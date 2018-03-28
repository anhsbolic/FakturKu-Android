package com.fakturku.aplikasi.ui.activity.costumerForm

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.view.MenuItem
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.model.Costumer
import com.fakturku.aplikasi.ui.fragment.costumerFragment.CostumerListFragment
import kotlinx.android.synthetic.main.activity_costumer_form.*

class CostumerFormActivity : AppCompatActivity(), CostumerFormContract.View {

    private lateinit var presenter: CostumerFormPresenter
    private var isUpdateCostumerMode: Boolean = false
    private lateinit var costumer: Costumer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_costumer_form)
        title = null
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_clear)

        //init presenter
        presenter = CostumerFormPresenter(this@CostumerFormActivity)

        //check intent and mode
        if (intent.hasExtra(INTENT_COSTUMER_DATA)){
            costumer = intent.getParcelableExtra(INTENT_COSTUMER_DATA)
            presenter.setUpdateMode(costumer)
        }

        //UI handling & listener
        costumerFormBtnSave.setOnClickListener {
            val name = costumerFormName.text.toString()
            val phone = costumerFormName.text.toString()
            val email = costumerFormName.text.toString()
            val city = costumerFormName.text.toString()
            val address = costumerFormName.text.toString()
            val costumerToAdd = Costumer(null, name, phone, email,address,city,null,null)

            if (!isUpdateCostumerMode){
                presenter.addCostumer(costumerToAdd)
            } else {
                presenter.updateCostumer(costumerToAdd)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                onBackPressed()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun setUpdateMode(costumer: Costumer) {
        isUpdateCostumerMode = true

        //update UI
        costumerFormName.setText(costumer.name)
        costumerFormEmail.setText(costumer.email)
        costumerFormPhone.setText(costumer.phone)
        costumerFormCity.setText(costumer.city)
        costumerFormAddress.setText(costumer.address)
        val strUpdate = "Update"
        costumerFormBtnSave.text = strUpdate
    }

    override fun showAddCostumerSuccess(costumer: Costumer) {
        val msg = "${costumer.name} berhasil ditambahkan"
        Snackbar.make(costumerFormCoordinatorLayout, msg, Snackbar.LENGTH_SHORT).show()

        Handler().postDelayed({
            val intentAddCostumerSuccess = Intent()
            setResult(CostumerListFragment.INTENT_ADD_COSTUMER_SUCCESS, intentAddCostumerSuccess)
            finish()
        }, 1200)
    }

    override fun showUpdateCostumerSuccess(costumer: Costumer) {
        val msg = "${costumer.name} berhasil diupdate"
        Snackbar.make(costumerFormCoordinatorLayout, msg, Snackbar.LENGTH_SHORT).show()

        Handler().postDelayed({
            val intentUpdateCostumerSuccess = Intent()
            setResult(CostumerListFragment.INTENT_UPDATE_COSTUMER_SUCCESS, intentUpdateCostumerSuccess)
            finish()
        }, 1200)
    }

    companion object {
        const val INTENT_COSTUMER_DATA: String = "IntentCostumerData"
    }
}

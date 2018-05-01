package com.fakturku.aplikasi.ui.activity.costumerForm

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.model.Costumer
import com.fakturku.aplikasi.ui.fragment.costumerFragment.CostumerListFragment
import kotlinx.android.synthetic.main.activity_costumer_form.*

class CostumerFormActivity : AppCompatActivity(), CostumerFormContract.View {

    private var userId: Long = 0
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

        //Get User Id
        if (intent.hasExtra(INTENT_USER_ID)) {
            userId = intent.getLongExtra(INTENT_USER_ID,0)
        }

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
            var phone: String? = null
            if (costumerFormPhone.text.toString().isNotEmpty()) {
                phone = costumerFormPhone.text.toString()
            }
            var email: String? = null
            if (costumerFormEmail.text.toString().isNotEmpty()) {
                email = costumerFormEmail.text.toString()
            }
            var city: String? = null
            if (costumerFormCity.text.toString().isNotEmpty()) {
                city = costumerFormCity.text.toString()
            }
            var address: String? = null
            if (costumerFormAddress.text.toString().isNotEmpty()) {
                address = costumerFormAddress.text.toString()
            }

            if (!isUpdateCostumerMode){
                presenter.addCostumer(userId, null, name, email, phone, city, address,
                        null, null, isUpdateCostumerMode)
            } else {
                presenter.updateCostumer(userId, costumer.id, name, email, phone, city, address,
                        costumer.created_at, null, isUpdateCostumerMode)
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

    override fun onBackPressed() {
        AlertDialog.Builder(this@CostumerFormActivity)
                .setMessage(R.string.costumer_form_backed_message)
                .setPositiveButton(R.string.costumer_form_backed_positive,{ _ , _ ->
                    super.onBackPressed()
                })
                .setNegativeButton(R.string.costumer_form_backed_negative, null)
                .show()
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

    override fun showErrorInput(isNameValid: Boolean, isEmailValid: Boolean) {
        if (!isNameValid){
            costumerFormName.error = "Isi dengan Nama Lengkap"
        } else {
            costumerFormName.error = null
        }

        if (!isEmailValid){
            costumerFormEmail.error = "Isi dengan Email"
        } else {
            costumerFormEmail.error = null
        }
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
        const val INTENT_USER_ID: String = "IntentUserId"
    }
}

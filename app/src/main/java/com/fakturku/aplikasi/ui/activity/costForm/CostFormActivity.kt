package com.fakturku.aplikasi.ui.activity.costForm

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.model.Cost
import com.fakturku.aplikasi.ui.fragment.costFragment.CostFragment
import kotlinx.android.synthetic.main.activity_cost_form.*

class CostFormActivity : AppCompatActivity(), CostFormContract.View {

    private lateinit var presenter : CostFormPresenter
    private lateinit var cost : Cost
    private var isUpdateCostMode : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cost_form)
        title = null
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_clear)

        //Init Presenter
        presenter = CostFormPresenter(this@CostFormActivity)

        //check intent data & mode
        if (intent.hasExtra(INTENT_COST_DATA)) {
            cost = intent.getParcelableExtra(INTENT_COST_DATA)
            presenter.setUpdateMode(cost)
        }

        //UI handling & listener
        costFormBtnSave.setOnClickListener {
            val name = costFormName.text.toString()
            val unitPrice = costFormCostPrice.text.toString()
            val info = costFormNotes.text.toString()

            var longCostPrice: Long? = null
            if (unitPrice.isNotEmpty()){
                longCostPrice = unitPrice.toLong()
            }

            if (!isUpdateCostMode) {
                presenter.addCost(null, name, longCostPrice, info,
                        null, null, isUpdateCostMode)
            } else {
                presenter.updateCost(cost.id, name, longCostPrice, info,
                        cost.created_at, null, isUpdateCostMode)
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
        AlertDialog.Builder(this@CostFormActivity)
                .setMessage(R.string.cost_form_backed_message)
                .setPositiveButton(R.string.cost_form_backed_positive,{ _ , _ ->
                    super.onBackPressed()
                })
                .setNegativeButton(R.string.cost_form_backed_negative, null)
                .show()
    }

    override fun setUpdateMode(cost: Cost) {
        isUpdateCostMode = true

        //Update UI
        costFormName.setText(cost.name)
        costFormCostPrice.setText(cost.unit_cost.toString())
        costFormNotes.setText(cost.info)
        val strUpdate = "Update"
        costFormBtnSave.text = strUpdate
    }

    override fun showErrorInput(isNameValid: Boolean) {
        if (!isNameValid){
            costFormName.error = "Isi dengan Nama Biaya"
        } else {
            costFormName.error = null
        }
    }

    override fun showAddCostSuccess(cost: Cost) {
        val msg = "${cost.name} berhasil ditambahkan"
        Snackbar.make(costFormCoordinatorLayout, msg, Snackbar.LENGTH_SHORT).show()

        Handler().postDelayed({
            val intentAddCostSuccess = Intent()
            setResult(CostFragment.INTENT_ADD_COST_SUCCESS, intentAddCostSuccess)
            finish()
        }, 1200)
    }

    override fun showUpdateCostSuccess(cost: Cost) {
        val msg = "${cost.name} berhasil diupdate"
        Snackbar.make(costFormCoordinatorLayout, msg, Snackbar.LENGTH_SHORT).show()

        Handler().postDelayed({
            val intentUpdateCostSuccess = Intent()
            setResult(CostFragment.INTENT_UPDATE_COST_SUCCESS, intentUpdateCostSuccess)
            finish()
        }, 1200)
    }

    companion object {
        const val INTENT_COST_DATA: String = "IntentCostData"
    }
}

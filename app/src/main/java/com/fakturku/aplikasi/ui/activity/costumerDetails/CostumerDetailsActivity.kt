package com.fakturku.aplikasi.ui.activity.costumerDetails

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.model.Costumer
import com.fakturku.aplikasi.ui.fragment.costumerFragment.CostumerListFragment
import kotlinx.android.synthetic.main.activity_costumer_details.*

class CostumerDetailsActivity : AppCompatActivity(), CostumerDetailsContract.View {

    private lateinit var presenter: CostumerDetailsPresenter
    private lateinit var costumer: Costumer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_costumer_details)
        title = null
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        //Init Presenter
        presenter = CostumerDetailsPresenter(this@CostumerDetailsActivity)

        if (intent.hasExtra(INTENT_DATA_COSTUMER)){
            costumer = intent.getParcelableExtra(INTENT_DATA_COSTUMER)
            title = costumer.name
            setCostumerData(costumer)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_costumer_details_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                onBackPressed()
                true
            }

            R.id.costumerDetailsMenuUpdate->{
                presenter.update(costumer)
                return true
            }

            R.id.costumerDetailsMenuDelete->{
                val strMsg = "Anda yakin akan menghapus ${costumer.name} dari database ?"
                val yesMsg = "Ya, Hapus"
                val noMsg = "Tidak"
                AlertDialog.Builder(this@CostumerDetailsActivity)
                        .setMessage(strMsg)
                        .setPositiveButton(yesMsg,{ _ , _ ->
                            presenter.delete(costumer)
                        })
                        .setNegativeButton(noMsg, null)
                        .show()
                return true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun setCostumerData(costumer: Costumer) {
        costumerDetailsName.text = costumer.name
        costumerDetailsEmail.text = costumer.email
        costumerDetailsPhone.text = costumer.phone
        costumerDetailsCity.text = costumer.city
        costumerDetailsAddress.text = costumer.address
    }

    override fun update(costumer: Costumer) {
        val intentUpdate = Intent()
        intentUpdate.putExtra(CostumerListFragment.INTENT_COSTUMER_DETAILS_UPDATE_DATA, costumer)
        setResult(CostumerListFragment.INTENT_COSTUMER_DETAILS_UPDATE, intentUpdate)
        finish()
    }

    override fun delete(costumer: Costumer) {
        val intentDelete = Intent()
        setResult(CostumerListFragment.INTENT_COSTUMER_DETAILS_DELETE, intentDelete)
        finish()
    }


    companion object {
        const val INTENT_DATA_COSTUMER: String = "IntentDataCostumer"
    }
}

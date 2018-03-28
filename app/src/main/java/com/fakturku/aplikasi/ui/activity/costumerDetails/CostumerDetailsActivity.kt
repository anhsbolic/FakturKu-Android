package com.fakturku.aplikasi.ui.activity.costumerDetails

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.model.Costumer
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
        costumerDetailsAddress.text = costumer.address
        costumerDetailsCity.text = costumer.city
    }

    override fun update(costumer: Costumer) {
        Toast.makeText(this@CostumerDetailsActivity, "FAKE UPDATE", Toast.LENGTH_SHORT).show()
    }

    override fun delete(costumer: Costumer) {
        Toast.makeText(this@CostumerDetailsActivity, "FAKE DELETE", Toast.LENGTH_SHORT).show()
    }


    companion object {
        const val INTENT_DATA_COSTUMER: String = "IntentDataCostumer"
    }
}

package com.fakturku.aplikasi.ui.activity.costDetails

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.model.Cost
import com.fakturku.aplikasi.ui.fragment.costFragment.CostFragment
import com.fakturku.aplikasi.utils.MyCurrencyFormat
import kotlinx.android.synthetic.main.activity_cost_details.*

class CostDetailsActivity : AppCompatActivity(), CostDetailsContract.View {

    private lateinit var presenter: CostDetailsPresenter
    private lateinit var cost: Cost

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cost_details)
        title = null
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        //Init Presenter
        presenter = CostDetailsPresenter(this@CostDetailsActivity)

        //Get Intent Data
        if (intent.hasExtra(INTENT_DATA_COST)) {
            cost = intent.getParcelableExtra(INTENT_DATA_COST)
            title = cost.name
            setCostData(cost)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_cost_details_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                onBackPressed()
                true
            }

            R.id.costDetailsMenuUpdate->{
                presenter.update(cost)
                return true
            }

            R.id.costDetailsMenuDelete->{
                val strMsg = "Anda yakin akan menghapus ${cost.name} dari database ?"
                val yesMsg = "Ya, Hapus"
                val noMsg = "Tidak"
                AlertDialog.Builder(this@CostDetailsActivity)
                        .setMessage(strMsg)
                        .setPositiveButton(yesMsg,{ _ , _ ->
                            presenter.delete(cost)
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

    override fun setCostData(cost: Cost) {
        costDetailsName.text = cost.name
        val costPrice = cost.unit_cost
        if (costPrice != null){
            costDetailsCostPrice.text = MyCurrencyFormat.rupiah(costPrice)
        }
        costDetailsNotes.text = cost.info
    }

    override fun update(cost: Cost) {
        val intentUpdate = Intent()
        intentUpdate.putExtra(CostFragment.INTENT_COST_DETAILS_UPDATE_DATA, cost)
        setResult(CostFragment.INTENT_COST_DETAILS_UPDATE, intentUpdate)
        finish()
    }

    override fun delete(cost: Cost) {
        val intentDelete = Intent()
        setResult(CostFragment.INTENT_COST_DETAILS_DELETE, intentDelete)
        finish()
    }

    companion object {
        const val INTENT_DATA_COST: String = "IntentDataCost"
    }
}

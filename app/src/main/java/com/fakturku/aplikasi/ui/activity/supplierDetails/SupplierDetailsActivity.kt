package com.fakturku.aplikasi.ui.activity.supplierDetails

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.model.Supplier
import com.fakturku.aplikasi.ui.fragment.supplierFragment.SupplierFragment
import kotlinx.android.synthetic.main.activity_supplier_details.*

class SupplierDetailsActivity : AppCompatActivity(), SupplierDetailsContract.View {

    private lateinit var presenter: SupplierDetailsPresenter
    private lateinit var supplier: Supplier

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supplier_details)
        title = null
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        //Init Presenter
        presenter = SupplierDetailsPresenter(this@SupplierDetailsActivity)

        //Get Intent Data
        if (intent.hasExtra(INTENT_DATA_SUPPLIER)) {
            supplier = intent.getParcelableExtra(INTENT_DATA_SUPPLIER)
            title = supplier.name
            setSupplierData(supplier)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_supplier_details_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                onBackPressed()
                true
            }

            R.id.supplierDetailsMenuUpdate->{
                presenter.update(supplier)
                return true
            }

            R.id.supplierDetailsMenuDelete->{
                val strMsg = "Anda yakin akan menghapus ${supplier.name} dari database ?"
                val yesMsg = "Ya, Hapus"
                val noMsg = "Tidak"
                AlertDialog.Builder(this@SupplierDetailsActivity)
                        .setMessage(strMsg)
                        .setPositiveButton(yesMsg,{ _ , _ ->
                            presenter.delete(supplier)
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

    override fun setSupplierData(supplier: Supplier) {
        supplierDetailsName.text = supplier.name
        supplierDetailsEmail.text = supplier.email
        supplierDetailsPhone.text = supplier.phone
        supplierDetailsAddress.text = supplier.address
        supplierDetailsCity.text = supplier.city
    }

    override fun update(supplier: Supplier) {
        val intentUpdate = Intent()
        intentUpdate.putExtra(SupplierFragment.INTENT_SUPPLIER_DETAILS_UPDATE_DATA, supplier)
        setResult(SupplierFragment.INTENT_SUPPLIER_DETAILS_UPDATE, intentUpdate)
        finish()
    }

    override fun delete(supplier: Supplier) {
        val intentDelete = Intent()
        setResult(SupplierFragment.INTENT_SUPPLIER_DETAILS_DELETE, intentDelete)
        finish()
    }

    companion object {
        const val INTENT_DATA_SUPPLIER: String = "IntentDataSupplier"
    }
}

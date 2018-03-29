package com.fakturku.aplikasi.ui.activity.supplierForm

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.model.Supplier
import com.fakturku.aplikasi.ui.fragment.supplierFragment.SupplierFragment
import kotlinx.android.synthetic.main.activity_supplier_form.*

class SupplierFormActivity : AppCompatActivity(), SupplierFormContract.View {

    private lateinit var presenter: SupplierFormPresenter
    private var isUpdateSupplierMode: Boolean = false
    private lateinit var supplier: Supplier

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supplier_form)
        title = null
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_clear)

        //Init Presenter
        presenter = SupplierFormPresenter(this@SupplierFormActivity)

        //Check the get Intent Data & Mode
        if (intent.hasExtra(INTENT_SUPPLIER_DATA)) {
            supplier = intent.getParcelableExtra(INTENT_SUPPLIER_DATA)
            presenter.setUpdateMode(supplier)
        }

        //UI handling & listener
        supplierFormBtnSave.setOnClickListener {
            val name = supplierFormName.text.toString()
            val phone = supplierFormPhone.text.toString()
            val email = supplierFormEmail.text.toString()
            val city = supplierFormCity.text.toString()
            val address = supplierFormAddress.text.toString()

            if (!isUpdateSupplierMode) {
                presenter.addSupplier(null, name, email, phone, city, address,
                        null, null, isUpdateSupplierMode)
            } else {
                presenter.updateSupplier(supplier.id, name, email, phone, city, address,
                        supplier.created_date, null, isUpdateSupplierMode)
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
        AlertDialog.Builder(this@SupplierFormActivity)
                .setMessage(R.string.supplier_form_backed_message)
                .setPositiveButton(R.string.supplier_form_backed_positive,{ _ , _ ->
                    super.onBackPressed()
                })
                .setNegativeButton(R.string.supplier_form_backed_negative, null)
                .show()
    }

    override fun setUpdateMode(supplier: Supplier) {
        isUpdateSupplierMode = true

        //update UI
        supplierFormName.setText(supplier.name)
        supplierFormEmail.setText(supplier.email)
        supplierFormPhone.setText(supplier.phone)
        supplierFormCity.setText(supplier.city)
        supplierFormAddress.setText(supplier.address)
        val strUpdate = "Update"
        supplierFormBtnSave.text = strUpdate
    }

    override fun showErrorInput(isNameValid: Boolean, isPhoneValid: Boolean, isEmailValid: Boolean) {
        if (!isNameValid){
            supplierFormName.error = "Isi dengan Nama Lengkap"
        } else {
            supplierFormName.error = null
        }

        if (!isPhoneValid){
            supplierFormPhone.error = "Isi dengan No HP"
        } else {
            supplierFormPhone.error = null
        }

        if (!isEmailValid){
            supplierFormEmail.error = "Isi dengan Email"
        } else {
            supplierFormEmail.error = null
        }
    }

    override fun showAddSupplierSuccess(supplier: Supplier) {
        val msg = "${supplier.name} berhasil ditambahkan"
        Snackbar.make(supplierFormCoordinatorLayout, msg, Snackbar.LENGTH_SHORT).show()

        Handler().postDelayed({
            val intentAddSupplierSuccess = Intent()
            setResult(SupplierFragment.INTENT_ADD_SUPPLIER_SUCCESS, intentAddSupplierSuccess )
            finish()
        }, 1200)
    }

    override fun showUpdateSupplierSuccess(supplier: Supplier) {
        val msg = "${supplier.name} berhasil diupdate"
        Snackbar.make(supplierFormCoordinatorLayout, msg, Snackbar.LENGTH_SHORT).show()

        Handler().postDelayed({
            val intentUpdateSupplierSuccess = Intent()
            setResult(SupplierFragment.INTENT_UPDATE_SUPPLIER_SUCCESS, intentUpdateSupplierSuccess)
            finish()
        }, 1200)
    }

    companion object {
        const val INTENT_SUPPLIER_DATA: String = "IntentSupplierData"
    }
}

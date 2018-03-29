package com.fakturku.aplikasi.ui.activity.supplierDetails

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.model.Supplier

class SupplierDetailsActivity : AppCompatActivity(), SupplierDetailsContract.View {

    private lateinit var presenter: SupplierDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supplier_details)
    }

    override fun setSupplierData(supplier: Supplier) {
    }

    override fun delete(supplier: Supplier) {
    }

    override fun update(supplier: Supplier) {
    }
}

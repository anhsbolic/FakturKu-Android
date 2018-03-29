package com.fakturku.aplikasi.ui.activity.supplierDetails

import com.fakturku.aplikasi.model.Supplier

class SupplierDetailsPresenter(private val view: SupplierDetailsContract.View)
    : SupplierDetailsContract.Presenter{

    override fun delete(supplier: Supplier) {
        view.delete(supplier)
    }

    override fun update(supplier: Supplier) {
        view.update(supplier)
    }

}
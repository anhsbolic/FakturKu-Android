package com.fakturku.aplikasi.ui.activity.invoiceForm

class InvoiceFormPresenter(private val view : InvoiceFormContract.View)
    : InvoiceFormContract.Presenter{

    override fun addPerson() {
        view.showAddPersonPage()
    }

}
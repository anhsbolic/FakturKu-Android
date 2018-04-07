package com.fakturku.aplikasi.ui.activity.invoiceDetails

import com.fakturku.aplikasi.model.Invoice

class InvoiceDetailsPresenter(private val view : InvoiceDetailsContract.View?)
    : InvoiceDetailsContract.Presenter{

    override fun setDataToUI(invoice: Invoice) {
        view?.updateUI(invoice)
    }

}
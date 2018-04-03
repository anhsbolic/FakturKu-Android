package com.fakturku.aplikasi.ui.fragment.invoiceFragment

class InvoicePresenter(private val view : InvoiceContract.View)
    : InvoiceContract.Presenter{

    override fun addSalesInvoice() {
        view.openAddSalesInvoice()
    }

    override fun addPurchaseInvoice() {
        view.openAddPurchaseInvoice()
    }

}
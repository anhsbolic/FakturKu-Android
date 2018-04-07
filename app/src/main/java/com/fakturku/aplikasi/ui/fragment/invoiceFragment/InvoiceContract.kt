package com.fakturku.aplikasi.ui.fragment.invoiceFragment

import com.fakturku.aplikasi.model.Invoice

interface InvoiceContract{

    interface View{
        fun initRecyclerView()

        fun showProgress()

        fun hideProgress()

        fun showPlaceholder()

        fun hidePlaceholder()

        fun showInvoiceList(invoiceList: MutableList<Invoice>)

        fun showInvoiceDetails(invoice: Invoice)

        fun showFabSubmenu()

        fun hideFabSubMenu()

        fun openAddSalesInvoice()

        fun openAddPurchaseInvoice()

        fun openAddCostInvoice()

    }

    interface Presenter{
        fun loadInvoiceListData(page: Int)

        fun loadInvoiceDebtListData(page: Int)

        fun loadInvoicePaidListData(page: Int)

        fun loadInvoiceDraftListData(page: Int)

        fun seeInvoiceDetails(invoice: Invoice)

        fun addSalesInvoice()

        fun addPurchaseInvoice()

        fun addCostInvoice()
    }
}

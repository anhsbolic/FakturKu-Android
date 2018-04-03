package com.fakturku.aplikasi.ui.fragment.invoiceFragment

interface InvoiceContract{

    interface View{
        fun showFabSubmenu()

        fun hideFabSubMenu()

        fun openAddSalesInvoice()

        fun openAddPurchaseInvoice()

        fun openAddCostInvoice()

    }

    interface Presenter{
        fun addSalesInvoice()

        fun addPurchaseInvoice()

        fun addCostInvoice()
    }
}
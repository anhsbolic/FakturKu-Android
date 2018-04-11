package com.fakturku.aplikasi.ui.activity.invoiceForm

interface InvoiceFormContract{

    interface View{
        fun showAddPersonPage()
    }

    interface Presenter{
        fun addPerson()
    }
}
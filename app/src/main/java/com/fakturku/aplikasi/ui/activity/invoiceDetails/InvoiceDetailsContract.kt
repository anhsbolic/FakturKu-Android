package com.fakturku.aplikasi.ui.activity.invoiceDetails

import com.fakturku.aplikasi.model.Invoice

interface InvoiceDetailsContract{

    interface View{
        fun updateUI(invoice: Invoice)
    }

    interface Presenter{
        fun setDataToUI(invoice: Invoice)
    }

}
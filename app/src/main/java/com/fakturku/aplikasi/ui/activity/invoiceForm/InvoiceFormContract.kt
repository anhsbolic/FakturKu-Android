package com.fakturku.aplikasi.ui.activity.invoiceForm

import java.util.*

interface InvoiceFormContract{

    interface View{
        fun showAddPersonPage()

        fun updateTransactionId(strTransactionId: String)

        fun updateItemTitle(strItemTitle: String)

        fun updateDueDate(strDueDate: String)
    }

    interface Presenter{
        fun addPerson()

        fun updateTransactionId(transactionType: Int, currentDate: Date)

        fun updateItemType(transactionType: Int)

        fun updateDueDate(dueDate: Date)
    }
}
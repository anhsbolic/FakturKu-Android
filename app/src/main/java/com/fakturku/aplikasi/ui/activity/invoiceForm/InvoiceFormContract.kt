package com.fakturku.aplikasi.ui.activity.invoiceForm

import com.fakturku.aplikasi.model.Product
import java.util.*

interface InvoiceFormContract{

    interface View{
        fun showAddPersonPage()

        fun updateTransactionId(strTransactionId: String)

        fun updateItemTitle(strItemTitle: String)

        fun updateDueDate(strDueDate: String)

        fun showAddItemPage()

        fun addSelectedItem(product: Product)

        fun clearProduct(product: Product, adapterPosition: Int)

        fun showSubtotal(intSubtotal: Int)
    }

    interface Presenter{
        fun addPerson()

        fun updateTransactionId(transactionType: Int, currentDate: Date)

        fun updateItemType(transactionType: Int)

        fun updateDueDate(dueDate: Date)

        fun addItem()

        fun addSelectedItem(product: Product)

        fun deleteProduct(product: Product, adapterPosition: Int)

        fun calculateSubtotal(totalItemPriceList: List<Int>)
    }
}
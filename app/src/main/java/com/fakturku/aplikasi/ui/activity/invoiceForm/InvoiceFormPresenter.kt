package com.fakturku.aplikasi.ui.activity.invoiceForm

import com.fakturku.aplikasi.model.Product
import com.fakturku.aplikasi.utils.MyDateFormatter
import java.util.*

class InvoiceFormPresenter(private val view : InvoiceFormContract.View)
    : InvoiceFormContract.Presenter{

    override fun updateDueDate(dueDate: Date) {
        val strDueDate = MyDateFormatter.dateToDateMonthYearBahasa(dueDate)
        view.updateDueDate(strDueDate)
    }

    override fun updateTransactionId(transactionType: Int, currentDate: Date) {
        val id = when(transactionType){
            SALES_TRANSACTION -> {"#S"}
            BUY_TRANSACTION -> {"#B"}
            COST_TRANSACTION -> {"#C"}
            else -> {""}
        }

        val strDate = MyDateFormatter.dateToYMDHM(currentDate)
        val idTransaction = "$id$strDate"
        view.updateTransactionId(idTransaction)
    }

    override fun updateItemType(transactionType: Int) {
        val itemTitle = when(transactionType){
            SALES_TRANSACTION -> {"BARANG"}
            BUY_TRANSACTION -> {"BARANG"}
            COST_TRANSACTION -> {"BIAYA"}
            else -> {""}
        }
        view.updateItemTitle(itemTitle)
    }

    override fun addPerson() {
        view.showAddPersonPage()
    }

    override fun addItem() {
        view.showAddItemPage()
    }

    override fun addSelectedItem(product: Product) {
        view.addSelectedItem(product)
    }

    companion object {
        const val SALES_TRANSACTION  = 0
        const val BUY_TRANSACTION  = 1
        const val COST_TRANSACTION  = 2

    }


}
package com.fakturku.aplikasi.ui.activity.invoiceForm

import com.fakturku.aplikasi.model.Product
import com.fakturku.aplikasi.utils.MyCurrencyFormat
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

    override fun deleteProduct(product: Product, adapterPosition: Int) {
        view.clearProduct(product, adapterPosition)
    }

    override fun calculateSubtotal(totalItemPriceList: List<Int>) {
        var intSubtotal = 0

        if (totalItemPriceList.isNotEmpty()) {
            for (i in 0 until totalItemPriceList.size) {
                intSubtotal += totalItemPriceList[i]
            }
        }

        view.showSubtotal(intSubtotal)
    }

    override fun calculateTotal(subTotal: Int, tax: Int) {
        val dblTax = 0.01 * tax * subTotal
        val total = subTotal - dblTax
        view.showTotal(total.toInt())
    }

    override fun calculateDueAmount(total: Int, paid: Int) {
        val dueAmount = total - paid
        view.showDueAmount(dueAmount)
    }

    companion object {
        const val SALES_TRANSACTION  = 0
        const val BUY_TRANSACTION  = 1
        const val COST_TRANSACTION  = 2

    }


}
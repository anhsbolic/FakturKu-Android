package com.fakturku.aplikasi.ui.fragment.invoiceFragment

import android.os.Handler
import com.fakturku.aplikasi.model.Invoice

class InvoicePresenter(private val view : InvoiceContract.View)
    : InvoiceContract.Presenter{

    override fun loadInvoiceListData(page: Int) {
        view.showProgress()

        Handler().postDelayed({
            //CREATE FAKE DATA
            val invoiceList: MutableList<Invoice> = ArrayList()
            val invoice1 = Invoice(
                    "#S20180405114300",
                    "2018-04-05",
                    0,
                    "Dedi",
                    null,
                    0.0,
                    400000,
                    0,
                    "2018-03-26",
                    "2018-03-26")
            val invoice2 = Invoice(
                    "#S201804051144",
                    "2018-04-05",
                    0,
                    "Dudi",
                    null,
                    0.0,
                    945000,
                    1,
                    "2018-03-26",
                    "2018-03-26")
            val invoice3 = Invoice(
                    "#B201804051155",
                    "2018-04-05",
                    1,
                    null,
                    "CV Gelas",
                    0.0,
                    12000000,
                    0,
                    "2018-03-26",
                    "2018-03-26")
            val invoice4 = Invoice(
                    "#C201804051143",
                    "2018-04-05 11:43:00",
                    2,
                    null,
                    "Toko Plastik",
                    0.0,
                    23000,
                    2,
                    "2018-03-26",
                    "2018-03-26")
            invoiceList.add(invoice1)
            invoiceList.add(invoice2)
            invoiceList.add(invoice3)
            invoiceList.add(invoice4)

            //SHOW DATA
            if (!invoiceList.isEmpty()){
                view.showInvoiceList(invoiceList)
                view.hidePlaceholder()
                view.hideProgress()
            } else {
                view.showPlaceholder()
                view.hideProgress()
            }
        }, 1200)
    }

    override fun loadInvoiceDebtListData(page: Int) {
        view.showProgress()

        Handler().postDelayed({
            //CREATE FAKE DATA
            val invoiceList: MutableList<Invoice> = ArrayList()
            val invoice1 = Invoice(
                    "#S20180405114300",
                    "2018-04-05",
                    0,
                    "Dedi",
                    null,
                    0.0,
                    400000,
                    0,
                    "2018-03-26",
                    "2018-03-26")
            val invoice3 = Invoice(
                    "#B201804051155",
                    "2018-04-05",
                    1,
                    null,
                    "CV Gelas",
                    0.0,
                    12000000,
                    0,
                    "2018-03-26",
                    "2018-03-26")
            invoiceList.add(invoice1)
            invoiceList.add(invoice3)

            //SHOW DATA
            if (!invoiceList.isEmpty()){
                view.showInvoiceList(invoiceList)
                view.hidePlaceholder()
                view.hideProgress()
            } else {
                view.showPlaceholder()
                view.hideProgress()
            }
        }, 1200)
    }

    override fun loadInvoicePaidListData(page: Int) {
        view.showProgress()

        Handler().postDelayed({
            //CREATE FAKE DATA
            val invoiceList: MutableList<Invoice> = ArrayList()
            val invoice2 = Invoice(
                    "#S201804051144",
                    "2018-04-05",
                    0,
                    "Dudi",
                    null,
                    0.0,
                    945000,
                    1,
                    "2018-03-26",
                    "2018-03-26")
            invoiceList.add(invoice2)

            //SHOW DATA
            if (!invoiceList.isEmpty()){
                view.showInvoiceList(invoiceList)
                view.hidePlaceholder()
                view.hideProgress()
            } else {
                view.showPlaceholder()
                view.hideProgress()
            }
        }, 1200)
    }

    override fun loadInvoiceDraftListData(page: Int) {
        view.showProgress()

        Handler().postDelayed({
            //CREATE FAKE DATA
            val invoiceList: MutableList<Invoice> = ArrayList()
            val invoice4 = Invoice(
                    "#C201804051143",
                    "2018-04-05 11:43:00",
                    2,
                    null,
                    "Toko Plastik",
                    0.0,
                    23000,
                    2,
                    "2018-03-26",
                    "2018-03-26")
            invoiceList.add(invoice4)

            //SHOW DATA
            if (!invoiceList.isEmpty()){
                view.showInvoiceList(invoiceList)
                view.hidePlaceholder()
                view.hideProgress()
            } else {
                view.showPlaceholder()
                view.hideProgress()
            }
        }, 1200)
    }

    override fun seeInvoiceDetails(invoice: Invoice) {
        view.showInvoiceDetails(invoice)
    }

    override fun addSalesInvoice() {
        view.openAddSalesInvoice()
    }

    override fun addPurchaseInvoice() {
        view.openAddPurchaseInvoice()
    }

    override fun addCostInvoice() {
        view.openAddCostInvoice()
    }
}
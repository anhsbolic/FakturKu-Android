package com.fakturku.aplikasi.ui.fragment.supplierFragment

import android.os.Handler
import com.fakturku.aplikasi.model.Supplier

class SupplierPresenter(private val view: SupplierContract.View)
    :SupplierContract.Presenter{

    override fun loadSupplierListData(page: Int) {
        view.showProgress()

        Handler().postDelayed({
            //CREATE FAKE DATA
            val supplierList: MutableList<Supplier> = ArrayList()
            val supplier1 = Supplier(
                    "1",
                    "Leslie Cremin DDS",
                    "+3538060548699",
                    "brant14@gmail.com",
                    "6741 Santino Plaza\nSouth Ledatown, DE 69067-6823",
                    "Kertzmannview",
                    "2018-03-26 11:43:00",
                    "2018-03-26 11:43:00")
            val supplier2 = Supplier(
                    "2",
                    "Prof. Ignatius Torp",
                    "+3763744488263",
                    "mckenzie87@yahoo.com",
                    "47605 Rath Knolls Apt. 307\nLake Erickatown, NV 96094-2418",
                    "Josephinemouth",
                    "2018-03-27 11:43:00",
                    "2018-03-27 11:43:00"
            )
            val supplier3 = Supplier(
                    "3",
                    "Cameron Pagac",
                    "+6708487416852",
                    "raphael28@gmail.com",
                    "24309 Crist Port Apt. 747\nHesselland, FL 68943-4439",
                    "New Kariville",
                    "2018-03-28 11:43:00",
                    "2018-03-28 11:43:00"
            )
            val supplier4 = Supplier(
                    "4",
                    "Miss Laury Rolfson",
                    "+7022972139539",
                    "wyman.sammie@yahoo.com",
                    "2832 Narciso Hill\nLeschstad, AZ 59579",
                    "South Kelli",
                    "2018-03-29 11:43:00",
                    "2018-03-29 11:43:00"
            )
            val supplier5 = Supplier(
                    "5",
                    "Joan Wilderman",
                    "+8420901142496",
                    "pollich.brielle@hotmail.com",
                    "964 Jeff Vista Suite 890\nLake Marianaville, MA 75430-3080",
                    "Delfinabury",
                    "2018-03-30 11:43:00",
                    "2018-03-30 11:43:00"
            )
            supplierList.add(supplier1)
            supplierList.add(supplier2)
            supplierList.add(supplier3)
            supplierList.add(supplier4)
            supplierList.add(supplier5)

            //SHOW DATA
            if (!supplierList.isEmpty()){
                view.showSupplierList(supplierList)
                view.hidePlaceholder()
                view.hideProgress()
            } else {
                view.showPlaceholder()
                view.hideProgress()
            }
        }, 1200)
    }

    override fun addSupplier() {
        view.openAddSupplierPage()
    }

    override fun updateSupplier(supplier: Supplier) {
        view.openUpdateSupplierPage(supplier)
    }

    override fun seeSupplierDetails(supplier: Supplier) {
        view.showSupplierDetails(supplier)
    }

}
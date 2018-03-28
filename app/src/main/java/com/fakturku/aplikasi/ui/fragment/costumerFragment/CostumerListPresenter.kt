package com.fakturku.aplikasi.ui.fragment.costumerFragment

import android.os.Handler
import com.fakturku.aplikasi.model.Costumer

class CostumerListPresenter(private val view: CostumerListContract.View)
    : CostumerListContract.Presenter{

    override fun loadCostumerListData(page: Int) {
        view.showProgress()

        Handler().postDelayed({
            //CREATE FAKE DATA
            val costumerList: MutableList<Costumer> = ArrayList()
            val costumer1 = Costumer(
                    "1",
                    "Leslie Cremin DDS",
                    "+3538060548699",
                    "brant14@gmail.com",
                    "6741 Santino Plaza\nSouth Ledatown, DE 69067-6823",
                    "Kertzmannview",
                    "2018-03-26 11:43:00",
                    "2018-03-26 11:43:00")
            val costumer2 = Costumer(
                    "2",
                    "Prof. Ignatius Torp",
                    "+3763744488263",
                    "mckenzie87@yahoo.com",
                    "47605 Rath Knolls Apt. 307\nLake Erickatown, NV 96094-2418",
                    "Josephinemouth",
                    "2018-03-27 11:43:00",
                    "2018-03-27 11:43:00"
            )
            val costumer3 = Costumer(
                    "3",
                    "Cameron Pagac",
                    "+6708487416852",
                    "raphael28@gmail.com",
                    "24309 Crist Port Apt. 747\nHesselland, FL 68943-4439",
                    "New Kariville",
                    "2018-03-28 11:43:00",
                    "2018-03-28 11:43:00"
            )
            val costumer4 = Costumer(
                    "4",
                    "Miss Laury Rolfson",
                    "+7022972139539",
                    "wyman.sammie@yahoo.com",
                    "2832 Narciso Hill\nLeschstad, AZ 59579",
                    "South Kelli",
                    "2018-03-29 11:43:00",
                    "2018-03-29 11:43:00"
            )
            val costumer5 = Costumer(
                    "5",
                    "Joan Wilderman",
                    "+8420901142496",
                    "pollich.brielle@hotmail.com",
                    "964 Jeff Vista Suite 890\nLake Marianaville, MA 75430-3080",
                    "Delfinabury",
                    "2018-03-30 11:43:00",
                    "2018-03-30 11:43:00"
            )
            costumerList.add(costumer1)
            costumerList.add(costumer2)
            costumerList.add(costumer3)
            costumerList.add(costumer4)
            costumerList.add(costumer5)

            //SHOW DATA
            if (!costumerList.isEmpty()){
                view.showCostumerList(costumerList)
                view.hidePlaceholder()
                view.hideProgress()
            } else {
                view.showPlaceholder()
                view.hideProgress()
            }
        }, 1200)
    }

    override fun addCostumer() {
        view.openAddCostumerPage()
    }

    override fun seeCostumerDetails(costumer: Costumer) {
        view.showCustomerDetails(costumer)
    }
}
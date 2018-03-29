package com.fakturku.aplikasi.ui.fragment.costFragment

import android.os.Handler
import com.fakturku.aplikasi.model.Cost

class CostPresenter(private val view : CostContract.View)
    : CostContract.Presenter{

    override fun loadCostListData(page: Int) {
        view.showProgress()

        Handler().postDelayed({
            //CREATE FAKE DATA
            val costList: MutableList<Cost> = ArrayList()
            val cost1 = Cost(
                    "1",
                    "Bensin",
                    20000,
                    "Harga per item",
                    "2018-03-26 11:43:00",
                    "2018-03-26 11:43:00")
            val cost2 = Cost(
                    "2",
                    "Pulsa",
                    15000,
                    "Harga per item",
                    "2018-03-26 11:43:00",
                    "2018-03-26 11:43:00")
            val cost3 = Cost(
                    "3",
                    "Parkir",
                    10000,
                    "Harga per item",
                    "2018-03-26 11:43:00",
                    "2018-03-26 11:43:00")
            val cost4 = Cost(
                    "4",
                    "Plastik",
                    10000,
                    "Harga per item",
                    "2018-03-26 11:43:00",
                    "2018-03-26 11:43:00")
            val cost5 = Cost(
                    "5",
                    "Kardus",
                    80000,
                    "Harga per item",
                    "2018-03-26 11:43:00",
                    "2018-03-26 11:43:00")
            costList.add(cost1)
            costList.add(cost2)
            costList.add(cost3)
            costList.add(cost4)
            costList.add(cost5)

            //SHOW DATA
            if (!costList.isEmpty()){
                view.showCostList(costList)
                view.hidePlaceholder()
                view.hideProgress()
            } else {
                view.showPlaceholder()
                view.hideProgress()
            }
        }, 1200)
    }

    override fun addCost() {
        view.openAddCostPage()
    }

    override fun updateCost(cost: Cost) {
        view.openUpdateCostPage(cost)
    }

    override fun seeCostDetails(cost: Cost) {
        view.showCostDetails(cost)
    }

}
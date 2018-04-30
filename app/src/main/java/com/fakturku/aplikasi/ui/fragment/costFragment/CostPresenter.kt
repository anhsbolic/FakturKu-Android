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
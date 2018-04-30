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

    override fun updateCostumer(costumer: Costumer) {
        view.openUpdateCostumerPage(costumer)
    }

    override fun seeCostumerDetails(costumer: Costumer) {
        view.showCustomerDetails(costumer)
    }
}
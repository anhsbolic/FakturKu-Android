package com.fakturku.aplikasi.ui.fragment.costFragment

import com.fakturku.aplikasi.model.Cost

interface CostContract{

    interface View{
        fun initRecyclerView()

        fun showProgress()

        fun hideProgress()

        fun showPlaceholder()

        fun hidePlaceholder()

        fun showCostList(productList: MutableList<Cost>)

        fun showCostDetails(cost: Cost)

        fun openAddCostPage()

        fun openUpdateCostPage(cost: Cost)
    }

    interface Presenter{
        fun loadCostListData(page: Int)

        fun addCost()

        fun updateCost(cost: Cost)

        fun seeCostDetails(cost: Cost)
    }
}
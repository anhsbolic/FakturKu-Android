package com.fakturku.aplikasi.ui.fragment.costFragment

import com.fakturku.aplikasi.model.Cost

interface CostContract{

    interface View{
        fun initRecyclerView()

        fun showProgress()

        fun hideProgress()

        fun showPlaceholder()

        fun hidePlaceholder()

        fun showCostList(costList: MutableList<Cost>)

        fun setLastPage(lastPage: Int)

        fun showCostDetails(cost: Cost)

        fun openAddCostPage(userId: Long)

        fun openUpdateCostPage(userId: Long, cost: Cost)
    }

    interface Presenter{
        fun loadCostListData(userId: Long, page: Int)

        fun addCost(userId: Long)

        fun updateCost(userId: Long, cost: Cost)

        fun seeCostDetails(cost: Cost)
    }
}
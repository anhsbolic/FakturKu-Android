package com.fakturku.aplikasi.ui.fragment.costumerFragment

import com.fakturku.aplikasi.model.Costumer

interface CostumerListContract{
    interface View{
        fun initRecyclerView()

        fun showProgress()

        fun hideProgress()

        fun showPlaceholder()

        fun hidePlaceholder()

        fun showCostumerList(costumerList: MutableList<Costumer>)

        fun setLastPage(lastPage: Int)

        fun showCustomerDetails(userId: Long, costumer: Costumer)

        fun openAddCostumerPage(userId: Long)

        fun openUpdateCostumerPage(userId: Long, costumer: Costumer)

    }

    interface Presenter{
        fun loadCostumerListData(userId: Long, page: Int)

        fun addCostumer(userId: Long)

        fun updateCostumer(userId: Long, costumer: Costumer)

        fun seeCostumerDetails(userId: Long, costumer: Costumer)
    }
}
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

        fun showCustomerDetails(costumer: Costumer)

        fun openAddCostumerPage()
    }

    interface Presenter{
        fun loadCostumerListData(page: Int)

        fun addCostumer()

        fun seeCostumerDetails(costumer: Costumer)
    }
}
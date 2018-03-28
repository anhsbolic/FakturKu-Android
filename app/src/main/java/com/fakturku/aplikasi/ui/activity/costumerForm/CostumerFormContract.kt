package com.fakturku.aplikasi.ui.activity.costumerForm

import com.fakturku.aplikasi.model.Costumer

interface CostumerFormContract{
    interface View{
        fun setUpdateMode(costumer: Costumer)

        fun showAddCostumerSuccess(costumer: Costumer)

        fun showUpdateCostumerSuccess(costumer: Costumer)
    }

    interface Presenter{
        fun setUpdateMode(costumer: Costumer)

        fun addCostumer(costumer: Costumer)

        fun updateCostumer(costumer: Costumer)
    }
}
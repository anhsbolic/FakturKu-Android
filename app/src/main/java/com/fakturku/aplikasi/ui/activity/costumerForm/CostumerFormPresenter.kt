package com.fakturku.aplikasi.ui.activity.costumerForm

import com.fakturku.aplikasi.model.Costumer

class CostumerFormPresenter(private val view: CostumerFormContract.View)
    : CostumerFormContract.Presenter{

    override fun setUpdateMode(costumer: Costumer) {
        view.setUpdateMode(costumer)
    }

    override fun addCostumer(costumer: Costumer) {
        view.showAddCostumerSuccess(costumer)
    }

    override fun updateCostumer(costumer: Costumer) {
        view.showUpdateCostumerSuccess(costumer)
    }

}
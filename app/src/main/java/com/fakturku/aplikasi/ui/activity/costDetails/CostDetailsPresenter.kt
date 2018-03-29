package com.fakturku.aplikasi.ui.activity.costDetails

import com.fakturku.aplikasi.model.Cost

class CostDetailsPresenter(private val view : CostDetailsContract.View)
    : CostDetailsContract.Presenter{

    override fun delete(cost: Cost) {
        view.delete(cost)
    }

    override fun update(cost: Cost) {
        view.update(cost)
    }

}
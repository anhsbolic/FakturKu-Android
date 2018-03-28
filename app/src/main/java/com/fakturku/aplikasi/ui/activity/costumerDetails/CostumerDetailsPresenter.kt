package com.fakturku.aplikasi.ui.activity.costumerDetails

import com.fakturku.aplikasi.model.Costumer

class CostumerDetailsPresenter(private val view: CostumerDetailsContract.View)
    : CostumerDetailsContract.Presenter{

    override fun delete(costumer: Costumer) {
        view.delete(costumer)
    }

    override fun update(costumer: Costumer) {
        view.update(costumer)
    }

}
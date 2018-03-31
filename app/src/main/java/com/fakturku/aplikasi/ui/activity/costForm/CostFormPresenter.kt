package com.fakturku.aplikasi.ui.activity.costForm

import com.fakturku.aplikasi.model.Cost

class CostFormPresenter(private val view : CostFormContract.View)
    : CostFormContract.Presenter{

    override fun setUpdateMode(cost: Cost) {
        view.setUpdateMode(cost)
    }

    override fun addCost(id: String?, name: String, costPrice: Int?, notes: String?, createdDate: String?, updatedDate: String?, isEditMode: Boolean) {
        validateInput(id, name, costPrice, notes, createdDate, updatedDate, isEditMode)
    }

    override fun updateCost(id: String?, name: String, costPrice: Int?, notes: String?, createdDate: String?, updatedDate: String?, isEditMode: Boolean) {
        validateInput(id, name, costPrice, notes, createdDate, updatedDate, isEditMode)
    }

    override fun validateInput(id: String?, name: String, costPrice: Int?, notes: String?, createdDate: String?, updatedDate: String?, isEditMode: Boolean) {
        var isNameValid = false

        if (name.isNotEmpty()){
            isNameValid = true
        }

        if (isNameValid ) {
            val cost = Cost(id, name, costPrice, notes, createdDate, updatedDate)

            if (!isEditMode){
                view.showAddCostSuccess(cost)
            } else {
                view.showUpdateCostSuccess(cost)
            }
        } else {
            view.showErrorInput(isNameValid)
        }
    }
}
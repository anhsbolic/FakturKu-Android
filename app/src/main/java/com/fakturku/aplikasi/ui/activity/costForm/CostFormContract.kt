package com.fakturku.aplikasi.ui.activity.costForm

import com.fakturku.aplikasi.model.Cost

interface CostFormContract{

    interface View{
        fun setUpdateMode(cost: Cost)

        fun showErrorInput(isNameValid: Boolean)

        fun showAddCostSuccess(cost: Cost)

        fun showUpdateCostSuccess(cost: Cost)
    }

    interface Presenter{
        fun setUpdateMode(cost: Cost)

        fun addCost(id: String?, name: String, costPrice: Int?, notes: String?,
                       createdDate: String?, updatedDate: String?, isEditMode: Boolean)

        fun updateCost(id: String?, name: String, costPrice: Int?, notes: String?,
                          createdDate: String?, updatedDate: String?, isEditMode: Boolean)

        fun validateInput(id: String?, name: String, costPrice: Int?, notes: String?,
                          createdDate: String?, updatedDate: String?, isEditMode: Boolean)
    }
}
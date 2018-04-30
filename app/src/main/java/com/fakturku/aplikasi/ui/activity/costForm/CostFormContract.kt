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

        fun addCost(userId:Long, costId: Long?, name: String, unitPrice: Long?, info: String?,
                       createdAt: String?, updatedAt: String?, isEditMode: Boolean)

        fun updateCost(userId:Long, costId: Long?, name: String, unitPrice: Long?, info: String?,
                          createdAt: String?, updatedAt: String?, isEditMode: Boolean)

        fun validateInput(userId:Long, costId: Long?, name: String, unitPrice: Long?, info: String?,
                          createdAt: String?, updatedAt: String?, isEditMode: Boolean)
    }
}
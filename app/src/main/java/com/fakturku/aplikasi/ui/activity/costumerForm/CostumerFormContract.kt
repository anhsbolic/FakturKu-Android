package com.fakturku.aplikasi.ui.activity.costumerForm

import com.fakturku.aplikasi.model.Costumer

interface CostumerFormContract{
    interface View{
        fun setUpdateMode(costumer: Costumer)

        fun showErrorInput(isNameValid: Boolean, isEmailValid: Boolean)

        fun showAddCostumerSuccess(costumer: Costumer)

        fun showUpdateCostumerSuccess(costumer: Costumer)
    }

    interface Presenter{
        fun setUpdateMode(costumer: Costumer)

        fun addCostumer(userId: Long, costumerId: Long?, name: String, email: String?, phone: String?, city: String?,
                        address: String?, createdAt: String?, updatedAt: String?, isEditMode: Boolean)

        fun updateCostumer(userId: Long, costumerId: Long?, name: String, email: String?, phone: String?, city: String?,
                           address: String?, createdAt: String?, updatedAt: String?, isEditMode: Boolean)

        fun validateInput(userId: Long, costumerId: Long?, name: String, email: String?, phone: String?, city: String?,
                          address: String?, createdAt: String?, updatedAt: String?, isEditMode: Boolean)

    }
}
package com.fakturku.aplikasi.ui.activity.costumerForm

import com.fakturku.aplikasi.model.Costumer

interface CostumerFormContract{
    interface View{
        fun setUpdateMode(costumer: Costumer)

        fun showErrorInput(isNameValid: Boolean, isPhoneValid: Boolean, isEmailValid: Boolean)

        fun showAddCostumerSuccess(costumer: Costumer)

        fun showUpdateCostumerSuccess(costumer: Costumer)
    }

    interface Presenter{
        fun setUpdateMode(costumer: Costumer)

        fun addCostumer(id: Long?, name: String, email: String, phone: String, city: String?,
                        address: String?, createdAt: String?, updatedAt: String?, isEditMode: Boolean)

        fun updateCostumer(id: Long?, name: String, email: String, phone: String, city: String?,
                           address: String?, createdAt: String?, updatedAt: String?, isEditMode: Boolean)

        fun validateInput(id: Long?, name: String, email: String, phone: String, city: String?,
                          address: String?, createdAt: String?, updatedAt: String?, isEditMode: Boolean)

    }
}
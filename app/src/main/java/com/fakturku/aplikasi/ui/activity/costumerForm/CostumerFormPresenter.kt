package com.fakturku.aplikasi.ui.activity.costumerForm

import com.fakturku.aplikasi.model.Costumer

class CostumerFormPresenter(private val view: CostumerFormContract.View)
    : CostumerFormContract.Presenter{


    override fun setUpdateMode(costumer: Costumer) {
        view.setUpdateMode(costumer)
    }

    override fun updateCostumer(id: Long?, name: String, email: String, phone: String, city: String?,
                                address: String?, createdAt: String?, updatedAt: String?,
                                isEditMode: Boolean) {
        validateInput(id, name, email, phone, city, address, createdAt, updatedAt, isEditMode)
    }

    override fun addCostumer(id: Long?, name: String, email: String, phone: String, city: String?,
                             address: String?, createdAt: String?, updatedAt: String?,
                             isEditMode: Boolean) {
        validateInput(id, name, email, phone, city, address, createdAt, updatedAt, isEditMode)
    }

    override fun validateInput(id: Long?, name: String, email: String, phone: String, city: String?,
                               address: String?, createdAt: String?, updatedAt: String?,
                               isEditMode: Boolean) {
        var isNameValidate = false
        var isPhoneValidate = false
        var isEmailValidate = false

        if (name.isNotEmpty()){
            isNameValidate = true
        }

        if (phone.isNotEmpty()){
            isPhoneValidate = true
        }

        if (email.isNotEmpty()){
            isEmailValidate = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        if (isNameValidate && isPhoneValidate && isEmailValidate) {
            val costumer = Costumer(id, name, phone, email, city, address, createdAt, updatedAt)

            if (!isEditMode){
                view.showAddCostumerSuccess(costumer)
            } else {
                view.showUpdateCostumerSuccess(costumer)
            }
        } else {
            view.showErrorInput(isNameValidate, isPhoneValidate, isEmailValidate)
        }

    }
}
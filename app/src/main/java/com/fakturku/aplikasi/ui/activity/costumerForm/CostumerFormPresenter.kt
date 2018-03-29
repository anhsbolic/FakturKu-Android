package com.fakturku.aplikasi.ui.activity.costumerForm

import com.fakturku.aplikasi.model.Costumer

class CostumerFormPresenter(private val view: CostumerFormContract.View)
    : CostumerFormContract.Presenter{


    override fun setUpdateMode(costumer: Costumer) {
        view.setUpdateMode(costumer)
    }

    override fun updateCostumer(id: String?, name: String, email: String, phone: String, city: String?,
                                address: String?, createdDate: String?, updatedDate: String?,
                                isEditMode: Boolean) {
        validateInput(id, name, email, phone, city, address, createdDate, updatedDate, isEditMode)
    }

    override fun addCostumer(id: String?, name: String, email: String, phone: String, city: String?,
                             address: String?, createdDate: String?, updatedDate: String?,
                             isEditMode: Boolean) {
        validateInput(id, name, email, phone, city, address, createdDate, updatedDate, isEditMode)
    }

    override fun validateInput(id: String?, name: String, email: String, phone: String, city: String?,
                               address: String?, createdDate: String?, updatedDate: String?,
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
            val costumer = Costumer(id, name, phone, email, address, city, createdDate, updatedDate)

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
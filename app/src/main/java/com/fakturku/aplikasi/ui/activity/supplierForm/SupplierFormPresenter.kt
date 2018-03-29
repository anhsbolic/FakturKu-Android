package com.fakturku.aplikasi.ui.activity.supplierForm

import com.fakturku.aplikasi.model.Supplier

class SupplierFormPresenter(private val view: SupplierFormContract.View)
    : SupplierFormContract.Presenter{

    override fun setUpdateMode(supplier: Supplier) {
        view.setUpdateMode(supplier)
    }

    override fun addSupplier(id: String?, name: String, email: String, phone: String, city: String?,
                             address: String?, createdDate: String?, updatedDate: String?,
                             isEditMode: Boolean) {
        validateInput(id, name, email, phone, city, address, createdDate, updatedDate, isEditMode)
    }

    override fun updateSupplier(id: String?, name: String, email: String, phone: String, city: String?,
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
            val supplier = Supplier(id, name, phone, email, address, city, createdDate, updatedDate)

            if (!isEditMode){
                view.showAddSupplierSuccess(supplier)
            } else {
                view.showUpdateSupplierSuccess(supplier)
            }
        } else {
            view.showErrorInput(isNameValidate, isPhoneValidate, isEmailValidate)
        }

    }

}
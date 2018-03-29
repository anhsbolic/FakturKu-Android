package com.fakturku.aplikasi.ui.activity.supplierForm

import com.fakturku.aplikasi.model.Supplier

interface SupplierFormContract{

    interface View{
        fun setUpdateMode(supplier: Supplier)

        fun showErrorInput(isNameValid: Boolean, isPhoneValid: Boolean, isEmailValid: Boolean)

        fun showAddSupplierSuccess(supplier: Supplier)

        fun showUpdateSupplierSuccess(supplier: Supplier)
    }

    interface Presenter{
        fun setUpdateMode(supplier: Supplier)

        fun addSupplier(id: String?, name: String, email: String, phone: String, city: String?,
                        address: String?, createdDate: String?, updatedDate: String?, isEditMode: Boolean)

        fun updateSupplier(id: String?, name: String, email: String, phone: String, city: String?,
                           address: String?, createdDate: String?, updatedDate: String?, isEditMode: Boolean)

        fun validateInput(id: String?, name: String, email: String, phone: String, city: String?,
                          address: String?, createdDate: String?, updatedDate: String?, isEditMode: Boolean)
    }

}
package com.fakturku.aplikasi.ui.activity.productForm

import com.fakturku.aplikasi.model.Product

interface ProductFormContract{

    interface View{

        fun setUpdateMode(product: Product)

        fun showErrorInput(isNameValid: Boolean)

        fun showAddProductSuccess(product: Product)

        fun showUpdateProductSuccess(product: Product)
    }

    interface Presenter{

        fun setUpdateMode(product: Product)

        fun addProduct(id: String?, name: String, buyPrice: Int?, sellPrice: Int?, notes: String?,
                        createdDate: String?, updatedDate: String?, isEditMode: Boolean)

        fun updateProduct(id: String?, name: String, buyPrice: Int?, sellPrice: Int?, notes: String?,
                           createdDate: String?, updatedDate: String?, isEditMode: Boolean)

        fun validateInput(id: String?, name: String, buyPrice: Int?, sellPrice: Int?, notes: String?,
                          createdDate: String?, updatedDate: String?, isEditMode: Boolean)

    }
}
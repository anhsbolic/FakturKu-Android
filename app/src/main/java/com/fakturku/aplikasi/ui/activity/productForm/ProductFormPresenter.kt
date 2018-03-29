package com.fakturku.aplikasi.ui.activity.productForm

import com.fakturku.aplikasi.model.Product

class ProductFormPresenter(private val view: ProductFormContract.View)
    : ProductFormContract.Presenter{

    override fun setUpdateMode(product: Product) {
        view.setUpdateMode(product)
    }

    override fun addCostumer(id: String?, name: String, buyPrice: Int?, sellPrice: Int?, notes: String?,
                             createdDate: String?, updatedDate: String?, isEditMode: Boolean) {
        validateInput(id, name, buyPrice, sellPrice, notes, createdDate, updatedDate, isEditMode)
    }

    override fun updateCostumer(id: String?, name: String, buyPrice: Int?, sellPrice: Int?, notes: String?,
                                createdDate: String?, updatedDate: String?, isEditMode: Boolean) {
        validateInput(id, name, buyPrice, sellPrice, notes, createdDate, updatedDate, isEditMode)
    }

    override fun validateInput(id: String?, name: String, buyPrice: Int?, sellPrice: Int?, notes: String?,
                               createdDate: String?, updatedDate: String?, isEditMode: Boolean) {

        var isNameValidate = false

        if (name.isNotEmpty()){
            isNameValidate = true
        }


        if (isNameValidate ) {
            val product = Product(id, name, buyPrice, sellPrice, notes, createdDate, updatedDate)

            if (!isEditMode){
                view.showAddProductSuccess(product)
            } else {
                view.showUpdateProductSuccess(product)
            }
        } else {
            view.showErrorInput(isNameValidate)
        }
    }

}
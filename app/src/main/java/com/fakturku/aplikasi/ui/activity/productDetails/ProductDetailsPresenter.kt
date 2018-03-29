package com.fakturku.aplikasi.ui.activity.productDetails

import com.fakturku.aplikasi.model.Product

class ProductDetailsPresenter(private val view : ProductDetailsContract.View)
    : ProductDetailsContract.Presenter{

    override fun delete(product: Product) {
        view.delete(product)
    }

    override fun update(product: Product) {
        view.update(product)
    }
}
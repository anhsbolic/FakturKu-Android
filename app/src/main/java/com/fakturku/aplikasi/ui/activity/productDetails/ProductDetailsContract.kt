package com.fakturku.aplikasi.ui.activity.productDetails

import com.fakturku.aplikasi.model.Product

interface ProductDetailsContract{

    interface View{
        fun setProductData(product: Product)

        fun delete(product: Product)

        fun update(product: Product)
    }

    interface Presenter{
        fun delete(product: Product)

        fun update(product: Product)
    }
}
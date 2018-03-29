package com.fakturku.aplikasi.ui.activity.productDetails

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.model.Product

class ProductDetailsActivity : AppCompatActivity(), ProductDetailsContract.View {

    private lateinit var presenter: ProductDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        //init presenter
        presenter = ProductDetailsPresenter(this@ProductDetailsActivity)
    }

    override fun setProductData(product: Product) {
    }

    override fun delete(product: Product) {
    }

    override fun update(product: Product) {
    }
}

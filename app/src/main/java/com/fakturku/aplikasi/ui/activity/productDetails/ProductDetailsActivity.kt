package com.fakturku.aplikasi.ui.activity.productDetails

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fakturku.aplikasi.R

class ProductDetailsActivity : AppCompatActivity(), ProductDetailsContract.View {

    private lateinit var presenter: ProductDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        //init presenter
        presenter = ProductDetailsPresenter(this@ProductDetailsActivity)
    }
}

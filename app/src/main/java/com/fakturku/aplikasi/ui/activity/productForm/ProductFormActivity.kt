package com.fakturku.aplikasi.ui.activity.productForm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fakturku.aplikasi.R

class ProductFormActivity : AppCompatActivity(), ProductFormContract.View {

    private lateinit var presenter: ProductFormPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_form)

        //Init Presenter
        presenter = ProductFormPresenter(this@ProductFormActivity)
    }
}

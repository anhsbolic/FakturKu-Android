package com.fakturku.aplikasi.ui.activity.costDetails

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fakturku.aplikasi.R

class CostDetailsActivity : AppCompatActivity(), CostDetailsContract.View {

    private lateinit var presenter: CostDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cost_details)

        //Init Presenter
        presenter = CostDetailsPresenter(this@CostDetailsActivity)
    }
}

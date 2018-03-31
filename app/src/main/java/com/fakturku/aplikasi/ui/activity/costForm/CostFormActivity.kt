package com.fakturku.aplikasi.ui.activity.costForm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fakturku.aplikasi.R

class CostFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cost_form)
        title = null
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_clear)

    }
}

package com.fakturku.aplikasi.ui.activity.search

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fakturku.aplikasi.R

class SearchActivity : AppCompatActivity() {

    private var searchMode: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        if (intent.hasExtra(INTENT_SEARCH_MODE)) {
            searchMode = intent.getIntExtra(INTENT_SEARCH_MODE,0)

            when (searchMode) {
                SEARCH_COSTUMER -> {
                    Toast.makeText(this@SearchActivity,"CARI COSTUMER", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        const val INTENT_SEARCH_MODE = "SearchMode"

        const val SEARCH_COSTUMER = 1
    }
}

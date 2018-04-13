package com.fakturku.aplikasi.ui.activity.search

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.fakturku.aplikasi.R
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(), SearchContract.View {

    private lateinit var presenter: SearchPresenter

    private var searchMode: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(searchToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back_white)
        searchToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        title = null

        //Init Presenter
        presenter = SearchPresenter(this@SearchActivity)

        //Get intent Data
        if (intent.hasExtra(INTENT_SEARCH_MODE)) {
            searchMode = intent.getIntExtra(INTENT_SEARCH_MODE,0)

            when (searchMode) {
                SEARCH_COSTUMER -> {
                    Toast.makeText(this@SearchActivity,"CARI COSTUMER", Toast.LENGTH_SHORT).show()
                }
            }
        }

        //Add New
        searchTxtAddNew.setOnClickListener {
            val name = searchEt.text.toString()
            if (name.isNotEmpty()) {
                presenter.addNew(name)
            }
        }

        //Query Search
        searchEt.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(editable: Editable?) {
                if (editable != null) {
                    if (editable.toString().trim().isNotEmpty()) {
                        val queryName = editable.toString().trim()
                        presenter.search(queryName)
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    override fun showSearchResult(queryName: String) {
        Toast.makeText(this@SearchActivity,queryName, Toast.LENGTH_SHORT).show()
    }

    override fun showNewForm(name: String) {
        Toast.makeText(this@SearchActivity,name, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val INTENT_SEARCH_MODE = "SearchMode"

        const val SEARCH_COSTUMER = 1
    }
}

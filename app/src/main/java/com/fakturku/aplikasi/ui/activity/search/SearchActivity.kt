package com.fakturku.aplikasi.ui.activity.search

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.fakturku.aplikasi.R
import kotlinx.android.synthetic.main.activity_search.*
import android.widget.ArrayAdapter
import com.fakturku.aplikasi.ui.activity.invoiceForm.InvoiceFormActivity


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
                        when (searchMode) {
                            SEARCH_COSTUMER -> {
                                presenter.searchCostumer(queryName)
                            }
                        }
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        //ListView listener
        searchLVResult.setOnItemClickListener { parent, _, position, _ ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            presenter.selectItem(selectedItem)
        }

    }

    override fun showSearchResult(queryList: List<String>) {
        //Fake ListView
        val listItems = arrayOfNulls<String>(queryList.size)
        for (i in 0 until queryList.size) {
            listItems[i] = queryList[i]
        }
        val queryAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        searchLVResult.adapter = queryAdapter
    }

    override fun showSelectedItem(name: String) {
        val intent = Intent()
        intent.putExtra(InvoiceFormActivity.INTENT_ADD_COSTUMER_DATA, name)
        setResult(InvoiceFormActivity.INTENT_ADD_COSTUMER_SUCCESS, intent)
        finish()
    }

    override fun showNewForm(name: String) {
        Toast.makeText(this@SearchActivity,name, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val INTENT_SEARCH_MODE = "SearchMode"

        const val SEARCH_COSTUMER = 1
    }
}

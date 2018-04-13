package com.fakturku.aplikasi.ui.activity.search

interface SearchContract{

    interface View{
        fun showNewForm(name: String)

        fun showSearchResult(queryList: List<String>)

        fun showSelectedItem(name: String)
    }

    interface Presenter{
        fun addNew(name: String)

        fun searchCostumer(queryName: String)

        fun selectItem(name: String)
    }
}
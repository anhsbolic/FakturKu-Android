package com.fakturku.aplikasi.ui.activity.search

interface SearchContract{

    interface View{
        fun showNewForm(name: String)

        fun showSearchResult(queryName: String)
    }

    interface Presenter{
        fun addNew(name: String)

        fun search(queryName: String)
    }
}
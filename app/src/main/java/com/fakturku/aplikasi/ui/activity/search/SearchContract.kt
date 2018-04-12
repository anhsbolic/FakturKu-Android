package com.fakturku.aplikasi.ui.activity.search

interface SearchContract{

    interface View{
        fun showNewForm(name: String)
    }

    interface Presenter{
        fun addNew(name: String)
    }
}
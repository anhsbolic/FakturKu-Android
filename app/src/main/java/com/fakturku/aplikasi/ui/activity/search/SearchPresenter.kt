package com.fakturku.aplikasi.ui.activity.search

class SearchPresenter(private val view: SearchContract.View)
    : SearchContract.Presenter{

    override fun addNew(name: String) {
        view.showNewForm(name)
    }

}
package com.fakturku.aplikasi.ui.activity.search

class SearchPresenter(private val view: SearchContract.View)
    : SearchContract.Presenter{

    private val fakeNameList = arrayOf(
            "Leslie Cremin DDS",
            "Prof. Ignatius Torp",
            "Cameron Pagac",
            "Miss Laury Rolfson",
            "Joan Wilderman"
    )

    override fun addNew(name: String) {
        view.showNewForm(name)
    }

    override fun searchCostumer(queryName: String) {
        val nameList : MutableList<String> = ArrayList()
        for (i in 0 until fakeNameList.size) {
            if (fakeNameList[i].contains(queryName, true)) {
                nameList.add(fakeNameList[i])
            }
        }
        view.showSearchResult(nameList)
    }

    override fun selectItem(name: String) {
        view.showSelectedItem(name)
    }
}
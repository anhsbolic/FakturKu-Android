package com.fakturku.aplikasi.ui.activity.search

import com.fakturku.aplikasi.model.Product

interface SearchContract{

    interface View{
        fun showNewForm(name: String)

        fun showSearchResult(queryList: List<String>)

        fun showSelectedItem(name: String)

        fun showSearchProductResult(productList: List<Product>)

        fun showSelectedProduct(product: Product)
    }

    interface Presenter{
        fun addNew(name: String)

        fun searchCostumer(queryName: String)

        fun selectItem(name: String)

        fun searchProduct(queryName: String)

        fun selectProduct(product: Product)
    }
}
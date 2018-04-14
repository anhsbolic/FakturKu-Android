package com.fakturku.aplikasi.ui.activity.search

import com.fakturku.aplikasi.model.Product

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

    override fun searchProduct(queryName: String) {
        val product1 = Product(
                "1",
                "Paprika Merah",
                20000,
                35000,
                "Harga per Kg",
                "2018-03-26 11:43:00",
                "2018-03-26 11:43:00")
        val product2 = Product(
                "2",
                "Paprika Hijau",
                15000,
                25000,
                "Harga per Kg",
                "2018-03-26 11:43:00",
                "2018-03-26 11:43:00")
        val product3 = Product(
                "3",
                "Tomat Cherry",
                10000,
                15000,
                "Harga per Kg",
                "2018-03-26 11:43:00",
                "2018-03-26 11:43:00")
        val product4 = Product(
                "4",
                "Lolorosa",
                18000,
                25000,
                "Harga per Kg",
                "2018-03-26 11:43:00",
                "2018-03-26 11:43:00")
        val product5 = Product(
                "5",
                "Butterhead",
                8000,
                15000,
                "Harga per Kg",
                "2018-03-26 11:43:00",
                "2018-03-26 11:43:00")
        val productList: MutableList<Product> = ArrayList()
        productList.add(product1)
        productList.add(product2)
        productList.add(product3)
        productList.add(product4)
        productList.add(product5)

        val queryProductList: MutableList<Product> = ArrayList()

        //Search
        for (i in 0 until productList.size) {
            if (productList[i].name!!.contains(queryName, true)) {
                queryProductList.add(productList[i])
            }
        }
        view.showSearchProductResult(queryProductList)
    }

    override fun selectProduct(product: Product) {
        view.showSelectedProduct(product)
    }
}
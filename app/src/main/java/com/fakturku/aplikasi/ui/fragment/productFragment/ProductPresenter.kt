package com.fakturku.aplikasi.ui.fragment.productFragment

import android.os.Handler
import com.fakturku.aplikasi.model.Product

class ProductPresenter(private val view: ProductContract.View)
    : ProductContract.Presenter{

    override fun loadProductListData(page: Int) {
        view.showProgress()

        Handler().postDelayed({
            //CREATE FAKE DATA
            val productList: MutableList<Product> = ArrayList()
            val product1 = Product(
                    "1",
                    "Paprika Merah",
                    "20000",
                    "35000",
                    "Harga per Kg",
                    "2018-03-26 11:43:00",
                    "2018-03-26 11:43:00")
            val product2 = Product(
                    "2",
                    "Paprika Hijau",
                    "15000",
                    "25000",
                    "Harga per Kg",
                    "2018-03-26 11:43:00",
                    "2018-03-26 11:43:00")
            val product3 = Product(
                    "3",
                    "Tomat Cherry",
                    "10000",
                    "15000",
                    "Harga per Kg",
                    "2018-03-26 11:43:00",
                    "2018-03-26 11:43:00")
            val product4 = Product(
                    "4",
                    "Lolorosa",
                    "18000",
                    "25000",
                    "Harga per Kg",
                    "2018-03-26 11:43:00",
                    "2018-03-26 11:43:00")
            val product5 = Product(
                    "5",
                    "Butterhead",
                    "8000",
                    "15000",
                    "Harga per Kg",
                    "2018-03-26 11:43:00",
                    "2018-03-26 11:43:00")
            productList.add(product1)
            productList.add(product2)
            productList.add(product3)
            productList.add(product4)
            productList.add(product5)

            //SHOW DATA
            if (!productList.isEmpty()){
                view.showProductList(productList)
                view.hidePlaceholder()
                view.hideProgress()
            } else {
                view.showPlaceholder()
                view.hideProgress()
            }
        }, 1200)
    }

    override fun addProduct() {
        view.openAddProductPage()
    }

    override fun updateProduct(product: Product) {
        view.openUpdateProductPage(product)
    }

    override fun seeProductDetails(product: Product) {
        view.showProductDetails(product)
    }

}
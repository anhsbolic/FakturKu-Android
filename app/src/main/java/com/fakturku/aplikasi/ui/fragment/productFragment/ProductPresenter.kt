package com.fakturku.aplikasi.ui.fragment.productFragment

import android.os.Handler
import android.util.Log
import com.fakturku.aplikasi.model.Product
import com.fakturku.aplikasi.networking.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ProductPresenter(private val view: ProductContract.View)
    : ProductContract.Presenter{

    //initialized gson
    val gson: Gson = GsonBuilder().create()

    //initialized retrofit
    val retrofit: Retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("http://127.0.0.1:8000/api/")
            .build()

    val apiService: ApiService = retrofit.create(
            ApiService::class.java)

    override fun loadProductListData(page: Int) {
        view.showProgress()

        apiService.getProductList(page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { productList ->
                            //SHOW DATA
                            if (!productList.data.isEmpty()){
                                view.showProductList(productList.data)
                                view.hidePlaceholder()
                                view.hideProgress()
                            } else {
                                view.showPlaceholder()
                                view.hideProgress()
                            }
                        },
                        {error ->
                            Log.e("Error", error.message)
                        })

//        Handler().postDelayed({
//            //CREATE FAKE DATA
//            val productList: MutableList<Product> = ArrayList()
//            val product1 = Product(
//                    "1",
//                    "Paprika Merah",
//                    20000,
//                    35000,
//                    "Harga per Kg",
//                    "2018-03-26 11:43:00",
//                    "2018-03-26 11:43:00")
//            val product2 = Product(
//                    "2",
//                    "Paprika Hijau",
//                    15000,
//                    25000,
//                    "Harga per Kg",
//                    "2018-03-26 11:43:00",
//                    "2018-03-26 11:43:00")
//            val product3 = Product(
//                    "3",
//                    "Tomat Cherry",
//                    10000,
//                    15000,
//                    "Harga per Kg",
//                    "2018-03-26 11:43:00",
//                    "2018-03-26 11:43:00")
//            val product4 = Product(
//                    "4",
//                    "Lolorosa",
//                    18000,
//                    25000,
//                    "Harga per Kg",
//                    "2018-03-26 11:43:00",
//                    "2018-03-26 11:43:00")
//            val product5 = Product(
//                    "5",
//                    "Butterhead",
//                    8000,
//                    15000,
//                    "Harga per Kg",
//                    "2018-03-26 11:43:00",
//                    "2018-03-26 11:43:00")
//            productList.add(product1)
//            productList.add(product2)
//            productList.add(product3)
//            productList.add(product4)
//            productList.add(product5)
//
//            //SHOW DATA
//            if (!productList.isEmpty()){
//                view.showProductList(productList)
//                view.hidePlaceholder()
//                view.hideProgress()
//            } else {
//                view.showPlaceholder()
//                view.hideProgress()
//            }
//        }, 1200)
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
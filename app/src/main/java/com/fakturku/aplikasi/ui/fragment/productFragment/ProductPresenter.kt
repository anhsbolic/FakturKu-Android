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
    private val gsonBuilder: Gson = GsonBuilder().create()

    //initialized retrofit
    private val retrofit: Retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
            .baseUrl(BASE_URL_DEV)
            .build()

    private val apiService: ApiService = retrofit.create(
            ApiService::class.java)

    override fun loadProductListData(page: Int) {
        view.showProgress()

        Handler().postDelayed({
            apiService.getProductList(page)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { productList ->
                                //SHOW DATA
                                if (!productList.data.isEmpty()){
                                    view.setTotalPage(productList.lastPage)
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

    companion object {
        const val BASE_URL_DEV = "http://127.0.0.1:8000/api/"
    }

}
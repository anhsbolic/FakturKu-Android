package com.fakturku.aplikasi.ui.activity.productDetails

import android.util.Log
import com.fakturku.aplikasi.model.Product
import com.fakturku.aplikasi.networking.ApiService
import com.fakturku.aplikasi.ui.fragment.productFragment.ProductPresenter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ProductDetailsPresenter(private val view : ProductDetailsContract.View)
    : ProductDetailsContract.Presenter{

    //initialized gson
    private val gsonBuilder: Gson = GsonBuilder().create()

    //initialized retrofit
    private val retrofit: Retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
            .baseUrl(ProductPresenter.BASE_URL_DEV)
            .build()

    private val apiService: ApiService = retrofit.create(
            ApiService::class.java)


    override fun delete(product: Product) {
        apiService.deleteProduct(product.id!!)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {deletedProduct->
                            view.delete(deletedProduct)
                        },
                        {error->
                            Log.e("Error", error.message)
                        })
    }

    override fun update(product: Product) {
        view.update(product)
    }
}
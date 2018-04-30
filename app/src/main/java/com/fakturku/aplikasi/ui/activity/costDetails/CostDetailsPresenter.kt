package com.fakturku.aplikasi.ui.activity.costDetails

import android.util.Log
import com.fakturku.aplikasi.model.Cost
import com.fakturku.aplikasi.networking.ApiService
import com.fakturku.aplikasi.ui.fragment.productFragment.ProductPresenter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CostDetailsPresenter(private val view : CostDetailsContract.View)
    : CostDetailsContract.Presenter{

    //initialized gson
    private val gsonBuilder: Gson = GsonBuilder().create()

    //initialized retrofit
    private val retrofit: Retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
            .baseUrl(ProductPresenter.BASE_URL_DEV)
            .build()

    //initialized API services
    private val apiService: ApiService = retrofit.create(
            ApiService::class.java)

    //Cost Details presenter functions
    override fun delete(userId: Long, cost: Cost) {
        apiService.deleteCost(userId, cost.id!!)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {deletedCost->
                            view.delete(deletedCost)
                        },
                        {error->
                            Log.e("Error", error.message)

                        })
    }

    override fun update(cost: Cost) {
        view.update(cost)
    }

}
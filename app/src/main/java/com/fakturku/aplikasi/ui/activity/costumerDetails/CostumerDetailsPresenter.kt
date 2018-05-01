package com.fakturku.aplikasi.ui.activity.costumerDetails

import android.util.Log
import com.fakturku.aplikasi.model.Costumer
import com.fakturku.aplikasi.networking.ApiService
import com.fakturku.aplikasi.ui.fragment.productFragment.ProductPresenter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CostumerDetailsPresenter(private val view: CostumerDetailsContract.View)
    : CostumerDetailsContract.Presenter{

    //initialized gson
    private val gsonBuilder: Gson = GsonBuilder().create()

    //initialized retrofit
    private val retrofit: Retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
            .baseUrl(ProductPresenter.BASE_URL_DEV)
            .build()

    //intialized API services
    private val apiService: ApiService = retrofit.create(
            ApiService::class.java)

    //Presenter Functions
    override fun delete(userId: Long, costumer: Costumer) {
        apiService.deleteCostumer(userId, costumer.id!!)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {deletedCostumer->
                            view.delete(deletedCostumer)
                        },
                        {error->
                            Log.e("Error", error.message)
                        })
    }

    override fun update(costumer: Costumer) {
        view.update(costumer)
    }

}
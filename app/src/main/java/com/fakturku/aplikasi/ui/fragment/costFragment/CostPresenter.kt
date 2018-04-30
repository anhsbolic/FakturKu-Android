package com.fakturku.aplikasi.ui.fragment.costFragment

import android.os.Handler
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

class CostPresenter(private val view : CostContract.View)
    : CostContract.Presenter{

    //initialized gson
    private val gsonBuilder: Gson = GsonBuilder().create()

    //initialized retrofit
    private val retrofit: Retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
            .baseUrl(ProductPresenter.BASE_URL_DEV)
            .build()

    //intialized API Services
    private val apiService: ApiService = retrofit.create(
            ApiService::class.java)

    //Cost Presenter Functions
    override fun loadCostListData(userId: Long, page: Int) {
        view.showProgress()

        Handler().postDelayed({
            //Load data
            apiService.getCostList(userId, page)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            {costList->
                                //SHOW DATA
                                if (!costList.data.isEmpty()){
                                    view.setLastPage(costList.lastPage)
                                    view.showCostList(costList.data)
                                    view.hidePlaceholder()
                                    view.hideProgress()
                                } else {
                                    view.showPlaceholder()
                                    view.hideProgress()
                                }
                            },
                            {error->
                                Log.e("Error", error.message)
                            })
        }, 1200)
    }

    override fun addCost(userId: Long) {
        view.openAddCostPage(userId)
    }

    override fun updateCost(userId: Long, cost: Cost) {
        view.openUpdateCostPage(userId, cost)
    }

    override fun seeCostDetails(userId: Long, cost: Cost) {
        view.showCostDetails(userId, cost)
    }

}
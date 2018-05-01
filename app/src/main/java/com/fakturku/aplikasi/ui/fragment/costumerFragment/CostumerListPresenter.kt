package com.fakturku.aplikasi.ui.fragment.costumerFragment

import android.os.Handler
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

class CostumerListPresenter(private val view: CostumerListContract.View)
    : CostumerListContract.Presenter{

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
    override fun loadCostumerListData(userId: Long, page: Int) {
        view.showProgress()

        Handler().postDelayed({
            //LOAD DATA
            apiService.getCostumerList(userId, page)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            {costumerList->
                                //SHOW DATA
                                if (!costumerList.data.isEmpty()){
                                    view.showCostumerList(costumerList.data)
                                    view.setLastPage(costumerList.lastPage)
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

    override fun addCostumer(userId: Long) {
        view.openAddCostumerPage(userId)
    }

    override fun updateCostumer(userId: Long, costumer: Costumer) {
        view.openUpdateCostumerPage(userId, costumer)
    }

    override fun seeCostumerDetails(userId: Long, costumer: Costumer) {
        view.showCustomerDetails(userId, costumer)
    }
}
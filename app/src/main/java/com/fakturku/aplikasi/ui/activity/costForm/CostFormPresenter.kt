package com.fakturku.aplikasi.ui.activity.costForm

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

class CostFormPresenter(private val view : CostFormContract.View)
    : CostFormContract.Presenter{

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
    override fun setUpdateMode(cost: Cost) {
        view.setUpdateMode(cost)
    }

    override fun addCost(userId: Long, costId: Long?, name: String, unitPrice: Long?, info: String?, createdAt: String?,
                         updatedAt: String?, isEditMode: Boolean) {
        validateInput(userId, costId, name, unitPrice, info, createdAt, updatedAt, isEditMode)
    }

    override fun updateCost(userId: Long, costId: Long?, name: String, unitPrice: Long?, info: String?,
                            createdAt: String?, updatedAt: String?, isEditMode: Boolean) {
        validateInput(userId, costId, name, unitPrice, info, createdAt, updatedAt, isEditMode)
    }

    override fun validateInput(userId: Long, costId: Long?, name: String, unitPrice: Long?, info: String?,
                               createdAt: String?, updatedAt: String?, isEditMode: Boolean) {
        var isNameValid = false

        if (name.isNotEmpty()){
            isNameValid = true
        }

        if (isNameValid ) {
            val cost = Cost(costId, name, unitPrice, info, createdAt, updatedAt)

            if (!isEditMode){
                apiService.saveCost(userId, cost.name!!, cost.unit_cost, cost.info)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {createdCost->
                                    view.showAddCostSuccess(createdCost)
                                },
                                {error->
                                    Log.e("Error", error.message)

                                })
            } else {
                apiService.updateCost(userId, cost.id!!, cost.name!!, cost.unit_cost, cost.info)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {updatedCost->
                                    view.showUpdateCostSuccess(updatedCost)
                                },
                                {error->
                                    Log.e("Error", error.message)

                                })
            }
        } else {
            view.showErrorInput(isNameValid)
        }
    }
}
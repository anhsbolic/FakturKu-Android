package com.fakturku.aplikasi.ui.activity.costumerForm

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

class CostumerFormPresenter(private val view: CostumerFormContract.View)
    : CostumerFormContract.Presenter{

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
    override fun setUpdateMode(costumer: Costumer) {
        view.setUpdateMode(costumer)
    }

    override fun updateCostumer(userId: Long, costumerId: Long?, name: String, email: String?, phone: String?,
                                city: String?, address: String?, createdAt: String?, updatedAt: String?,
                                isEditMode: Boolean) {
        validateInput(userId, costumerId, name, email, phone, city, address, createdAt, updatedAt, isEditMode)
    }

    override fun addCostumer(userId: Long, costumerId: Long?, name: String, email: String?, phone: String?,
                             city: String?, address: String?, createdAt: String?, updatedAt: String?,
                             isEditMode: Boolean) {
        validateInput(userId, costumerId, name, email, phone, city, address, createdAt, updatedAt, isEditMode)
    }

    override fun validateInput(userId: Long, costumerId: Long?, name: String, email: String?, phone: String?,
                               city: String?, address: String?, createdAt: String?, updatedAt: String?,
                               isEditMode: Boolean) {
        var isNameValidate = false
        var isEmailValidate = false

        if (name.isNotEmpty()){
            isNameValidate = true
        }

        if (email != null) {
            if (email.isNotEmpty()){
                isEmailValidate = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
            }
        } else {
            isEmailValidate = true
        }


        if (isNameValidate && isEmailValidate) {
            if (!isEditMode){
                apiService.saveCostumer(userId, name, phone, email, city, address)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {createdCostumer->
                                    view.showAddCostumerSuccess(createdCostumer)
                                },
                                {error->
                                    Log.e("Error", error.message)

                                })
            } else {
                apiService.updateCostumer(userId, costumerId!!, name, phone, email, city, address)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {updatedCostumer->
                                    view.showUpdateCostumerSuccess(updatedCostumer)
                                },
                                {error->
                                    Log.e("Error", error.message)
                                })
            }
        } else {
            view.showErrorInput(isNameValidate, isEmailValidate)
        }
    }
}
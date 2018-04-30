package com.fakturku.aplikasi.ui.activity.productForm

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

class ProductFormPresenter(private val view: ProductFormContract.View)
    : ProductFormContract.Presenter{

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

    override fun setUpdateMode(product: Product) {
        view.setUpdateMode(product)
    }

    override fun addProduct(userId: Long, productId:  Long?, name: String, buyPrice: Long?, sellPrice: Long?, notes: String?,
                             createdDate: String?, updatedDate: String?, isEditMode: Boolean) {
        validateInput(userId, productId, name, buyPrice, sellPrice, notes, createdDate, updatedDate, isEditMode)
    }

    override fun updateProduct(userId: Long, productId: Long?, name: String, buyPrice: Long?, sellPrice: Long?, notes: String?,
                                createdDate: String?, updatedDate: String?, isEditMode: Boolean) {
        validateInput(userId, productId, name, buyPrice, sellPrice, notes, createdDate, updatedDate, isEditMode)
    }

    override fun validateInput(userId: Long, productId: Long?, name: String, buyPrice: Long?, sellPrice: Long?, notes: String?,
                               createdDate: String?, updatedDate: String?, isEditMode: Boolean) {

        var isNameValidate = false

        if (name.isNotEmpty()){
            isNameValidate = true
        }


        if (isNameValidate ) {
            val product = Product(productId, name, buyPrice, sellPrice, notes, createdDate, updatedDate)
            Log.d("TES", product.toString())

            if (!isEditMode){
                apiService.saveProduct(userId, product.name!!, product.purchase_price!!, product.sell_price!!, product.info!!)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {savedProduct->
                                    view.showAddProductSuccess(savedProduct)
                                },
                                {error->
                                    Log.e("Error", error.message)
                                })
            } else {
                apiService.updateProduct(userId, product.id!!, product.name!!, product.purchase_price!!, product.sell_price!!, product.info!!)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {updatedProduct->
                                    view.showUpdateProductSuccess(updatedProduct)
                                },
                                {error->
                                    Log.e("Error", error.message)
                                })
            }
        } else {
            view.showErrorInput(isNameValidate)
        }
    }
}
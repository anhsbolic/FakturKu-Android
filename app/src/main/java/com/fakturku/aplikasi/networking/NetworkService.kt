package com.fakturku.aplikasi.networking

import com.fakturku.aplikasi.model.ProductList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{

    @GET("product")
    fun getProductList(@Query("page") page: Int): Observable<ProductList>
}
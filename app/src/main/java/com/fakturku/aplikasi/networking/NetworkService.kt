package com.fakturku.aplikasi.networking

import com.fakturku.aplikasi.model.Product
import com.fakturku.aplikasi.model.ProductList
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService{

    @GET("product")
    fun getProductList(@Query("page") page: Int): Observable<ProductList>

    @FormUrlEncoded
    @POST("product")
    fun saveProduct(@Field("name") name: String,
                    @Field("purchase_price") purchasePrice: Int,
                    @Field("sell_price") sellPrice: Int,
                    @Field("info") info: String): Observable<Product>
}

//TODO : change int to long
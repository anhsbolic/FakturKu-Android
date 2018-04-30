package com.fakturku.aplikasi.networking

import com.fakturku.aplikasi.model.Cost
import com.fakturku.aplikasi.model.CostList
import com.fakturku.aplikasi.model.Product
import com.fakturku.aplikasi.model.ProductList
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService{

    /* PRODUCT REST API */
    @GET("user/{userId}/product")
    fun getProductList(@Path("userId") userId: Long,
                       @Query("page") page: Int): Observable<ProductList>

    @FormUrlEncoded
    @POST("user/{userId}/product")
    fun saveProduct(@Path("userId") userId: Long,
                    @Field("name") name: String,
                    @Field("purchase_price") purchasePrice: Long,
                    @Field("sell_price") sellPrice: Long,
                    @Field("info") info: String): Observable<Product>

    @FormUrlEncoded
    @PUT("user/{userId}/product/{productId}")
    fun updateProduct(@Path("userId") userId: Long,
                      @Path("productId") productId: Long,
                      @Field("name") name: String,
                      @Field("purchase_price") purchasePrice: Long,
                      @Field("sell_price") sellPrice: Long,
                      @Field("info") info: String): Observable<Product>

    @DELETE("user/{userId}/product/{productId}")
    fun deleteProduct(@Path("userId") userId: Long,
                      @Path("productId") productId: Long): Observable<Product>

    /* COST REST API */
    @GET("user/{userId}/cost")
    fun getCostList(@Path("userId") userId: Long,
                       @Query("page") page: Int): Observable<CostList>

    @FormUrlEncoded
    @POST("user/{userId}/cost")
    fun saveCost(@Path("userId") userId: Long,
                    @Field("name") name: String,
                    @Field("unit_cost") unitCost: Long?,
                    @Field("info") info: String?): Observable<Cost>

    @FormUrlEncoded
    @PUT("user/{userId}/cost/{costId}")
    fun updateCost(@Path("userId") userId: Long,
                      @Path("costId") costId: Long,
                      @Field("name") name: String,
                      @Field("unit_cost") unitCost: Long?,
                      @Field("info") info: String?): Observable<Cost>

    @DELETE("user/{userId}/cost/{costId}")
    fun deleteCost(@Path("userId") userId: Long,
                      @Path("costId") costId: Long): Observable<Cost>

}
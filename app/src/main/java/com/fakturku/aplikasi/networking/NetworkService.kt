package com.fakturku.aplikasi.networking

import com.fakturku.aplikasi.model.CostumerList
import io.reactivex.Observable
import retrofit2.http.GET

interface NetworkService{

    @GET("costumers")
    fun getCostumerList(): Observable<CostumerList>
}
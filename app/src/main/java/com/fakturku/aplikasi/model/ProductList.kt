package com.fakturku.aplikasi.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductList(@SerializedName("data") @Expose var data: MutableList<Product> = ArrayList(),
                       @SerializedName("last_page") @Expose var lastPage: Int)
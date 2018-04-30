package com.fakturku.aplikasi.model

import android.support.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
data class CostList(@SerializedName("data") @Expose var data: MutableList<Cost> = ArrayList()
                    ,@SerializedName("last_page") @Expose var lastPage: Int)
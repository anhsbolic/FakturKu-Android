package com.fakturku.aplikasi.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CostList(@SerializedName("data") @Expose var data: List<Cost> = ArrayList())
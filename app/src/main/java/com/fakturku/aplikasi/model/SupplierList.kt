package com.fakturku.aplikasi.model

import android.support.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
data class SupplierList(@SerializedName("data") @Expose var data: List<Supplier> = ArrayList())
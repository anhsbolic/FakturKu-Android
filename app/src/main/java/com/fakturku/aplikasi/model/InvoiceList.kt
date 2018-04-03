package com.fakturku.aplikasi.model

import android.support.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
data class InvoiceList(@SerializedName("data") @Expose var data: List<Invoice> = ArrayList())
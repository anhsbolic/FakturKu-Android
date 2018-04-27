package com.fakturku.aplikasi.model

import android.os.Parcelable
import android.support.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Product(@SerializedName("id") @Expose var id: String?,
                   @SerializedName("name") @Expose var name : String?,
                   @SerializedName("purchase_price") @Expose var purchase_price : Int?,
                   @SerializedName("sell_price") @Expose var sell_price : Int?,
                   @SerializedName("info") @Expose var info : String?,
                   @SerializedName("created_at") @Expose var created_at : String?,
                   @SerializedName("updated_at") @Expose var updated_at : String?) : Parcelable {
    constructor(): this(
            null,
            null,
            null,
            null,
            null,
            null,
            null
    )
}
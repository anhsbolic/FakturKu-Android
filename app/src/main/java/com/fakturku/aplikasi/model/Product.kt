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
                   @SerializedName("buy_price") @Expose var buy_price : String?,
                   @SerializedName("sell_price") @Expose var sell_price : String?,
                   @SerializedName("notes") @Expose var notes : String?,
                   @SerializedName("created_date") @Expose var created_date : String?,
                   @SerializedName("updated_date") @Expose var updated_date : String?) : Parcelable {
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
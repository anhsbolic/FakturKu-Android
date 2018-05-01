package com.fakturku.aplikasi.model

import android.os.Parcelable
import android.support.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Costumer(@SerializedName("id") @Expose var id: Long?,
                    @SerializedName("name") @Expose var name : String?,
                    @SerializedName("phone") @Expose var phone : String?,
                    @SerializedName("email") @Expose var email : String?,
                    @SerializedName("city") @Expose var city : String?,
                    @SerializedName("address") @Expose var address : String?,
                    @SerializedName("created_at") @Expose var created_at : String?,
                    @SerializedName("updated_at") @Expose var updated_at : String?) : Parcelable {
    constructor(): this(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
    )
}
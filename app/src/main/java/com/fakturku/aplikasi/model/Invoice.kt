package com.fakturku.aplikasi.model

import android.os.Parcelable
import android.support.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Invoice(@SerializedName("id") @Expose var id: String?,
                   @SerializedName("due_date") @Expose var due_date : String?,
                   @SerializedName("id_transaction_type") @Expose var id_transaction_type : Int?,
                   @SerializedName("costumer") @Expose var costumer : String?,
                   @SerializedName("supplier") @Expose var supplier : String?,
                   @SerializedName("tax") @Expose var tax : Double?,
                   @SerializedName("total") @Expose var total : Int?,
                   @SerializedName("id_status") @Expose var id_status : Int?,
                   @SerializedName("created_date") @Expose var created_date : String?,
                   @SerializedName("updated_date") @Expose var updated_date : String?) : Parcelable {
    constructor(): this(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null)
}
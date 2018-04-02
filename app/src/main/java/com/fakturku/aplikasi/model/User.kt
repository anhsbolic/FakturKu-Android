package com.fakturku.aplikasi.model

import android.os.Parcelable
import android.support.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class User(@SerializedName("id") @Expose var id: String?,
                @SerializedName("name") @Expose var name : String?,
                @SerializedName("phone") @Expose var phone : String?,
                @SerializedName("email") @Expose var email : String?,
                @SerializedName("address") @Expose var address : String?,
                @SerializedName("city") @Expose var city : String?,
                @SerializedName("acc_num") @Expose var account_number : String?,
                @SerializedName("company_name") @Expose var company_name : String?,
                @SerializedName("company_phone") @Expose var company_phone : String?,
                @SerializedName("company_email") @Expose var company_email : String?,
                @SerializedName("company_address") @Expose var company_address : String?,
                @SerializedName("company_city") @Expose var company_city : String?,
                @SerializedName("company_acc_num") @Expose var company_account_number : String?,
                @SerializedName("created_date") @Expose var created_date : String?,
                @SerializedName("updated_date") @Expose var updated_date : String?) : Parcelable {

    constructor():this(
            null,
            null,
            null,
            null,
            null,
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
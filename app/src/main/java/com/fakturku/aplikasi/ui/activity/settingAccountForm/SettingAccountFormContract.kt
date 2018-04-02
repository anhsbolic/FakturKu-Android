package com.fakturku.aplikasi.ui.activity.settingAccountForm

import com.fakturku.aplikasi.model.User

interface SettingAccountFormContract{

    interface View{
        fun setUserDataToUI(user: User)

        fun showErrorEtCompanyEmail(isValid: Boolean)

        fun showUpdateDataSuccess(user: User)
    }

    interface Presenter{
        fun setUserData(user: User)

        fun saveUpdateUserData(id: String?,
                               name : String?,
                               phone : String?,
                               email : String?,
                               address : String?,
                               city : String?,
                               accountNumber : String?,
                               companyName : String?,
                               companyPhone : String?,
                               companyEmail : String?,
                               companyAddress : String?,
                               companyCity : String?,
                               companyAccountNumber : String?,
                               createdDate : String?,
                               updatedDate : String?)
    }
}
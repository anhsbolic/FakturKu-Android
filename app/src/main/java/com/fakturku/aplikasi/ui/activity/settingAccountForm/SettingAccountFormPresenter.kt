package com.fakturku.aplikasi.ui.activity.settingAccountForm

import com.fakturku.aplikasi.model.User

class SettingAccountFormPresenter(private val view : SettingAccountFormContract.View)
    : SettingAccountFormContract.Presenter{

    override fun setUserData(user: User) {
        view.setUserDataToUI(user)
    }

    override fun saveUpdateUserData(id: String?,
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
                                    updatedDate : String?) {

        if (companyEmail != null) {
            if (companyEmail.isNotEmpty()){
                val isInEmailFormat = android.util.Patterns.EMAIL_ADDRESS.matcher(companyEmail).matches()
                if (!isInEmailFormat) {
                    view.showErrorEtCompanyEmail(isInEmailFormat)
                } else {
                    view.showErrorEtCompanyEmail(isInEmailFormat)
                    val user = User(
                            id,
                            name,
                            phone,
                            email,
                            address,
                            city,
                            accountNumber,
                            companyName,
                            companyPhone,
                            companyEmail,
                            companyAddress,
                            companyCity,
                            companyAccountNumber,
                            createdDate,
                            updatedDate
                    )
                    view.showUpdateDataSuccess(user)
                }
            }

        } else {
            val user = User(
                    id,
                    name,
                    phone,
                    email,
                    address,
                    city,
                    accountNumber,
                    companyName,
                    companyPhone,
                    companyEmail,
                    companyAddress,
                    companyCity,
                    companyAccountNumber,
                    createdDate,
                    updatedDate
            )
            view.showUpdateDataSuccess(user)
        }

    }

}
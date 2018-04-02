package com.fakturku.aplikasi.ui.activity.settingAccountForm

import com.fakturku.aplikasi.model.User

interface SettingAccountFormContract{

    interface View{
        fun setUserDataToUI(user: User)

        fun showUpdateDataSuccess(user: User)
    }

    interface Presenter{
        fun setUserData(user: User)

        fun saveUpdateUserData(user: User)
    }
}
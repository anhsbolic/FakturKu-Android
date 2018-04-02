package com.fakturku.aplikasi.ui.activity.settingAccountForm

import com.fakturku.aplikasi.model.User

class SettingAccountFormPresenter(private val view : SettingAccountFormContract.View)
    : SettingAccountFormContract.Presenter{

    override fun setUserData(user: User) {
        view.setUserDataToUI(user)
    }

    override fun saveUpdateUserData(user: User) {
        view.showUpdateDataSuccess(user)
    }

}
package com.fakturku.aplikasi.ui.activity.settingAccount

import com.fakturku.aplikasi.model.User

class SettingAccountPresenter(private val view : SettingAccountContract.View)
    : SettingAccountContract.Presenter{

    override fun setDataToUI(user: User) {
        view.setDataToUI(user)
    }

    override fun editUser(user: User) {
        view.showEditAccount(user)
    }

}
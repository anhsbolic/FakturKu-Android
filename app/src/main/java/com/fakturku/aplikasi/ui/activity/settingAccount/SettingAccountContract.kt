package com.fakturku.aplikasi.ui.activity.settingAccount

import com.fakturku.aplikasi.model.User

interface SettingAccountContract{

    interface View{
        fun setDataToUI(user: User)

        fun showEditAccount(user: User)
    }

    interface Presenter{
        fun setDataToUI(user: User)

        fun editUser(user: User)
    }
}
package com.fakturku.aplikasi.ui.activity.settingAccountForm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.model.User

class SettingAccountFormActivity : AppCompatActivity(), SettingAccountFormContract.View {

    private lateinit var presenter: SettingAccountFormPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_account_form)

        //Init Presenter
        presenter = SettingAccountFormPresenter(this@SettingAccountFormActivity)
    }

    override fun setUserDataToUI(user: User) {
    }

    override fun showUpdateDataSuccess(user: User) {
    }
}

package com.fakturku.aplikasi.ui.fragment.settingsFragment

class MySettingsPresenter(private val view : MySettingsContract.View)
    : MySettingsContract.Presenter{

    override fun settingAccount() {
        view.showAccountSettings()
    }

    override fun settingInvoice() {
        view.showInvoiceSettings()
    }

}
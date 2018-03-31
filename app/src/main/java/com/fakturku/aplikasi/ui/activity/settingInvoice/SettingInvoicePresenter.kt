package com.fakturku.aplikasi.ui.activity.settingInvoice

class SettingInvoicePresenter(private val view : SettingInvoiceContract.View)
    : SettingInvoiceContract.Presenter{

    override fun settingInvoice() {
        view.showSetingInvoiceSuccess()
    }

}
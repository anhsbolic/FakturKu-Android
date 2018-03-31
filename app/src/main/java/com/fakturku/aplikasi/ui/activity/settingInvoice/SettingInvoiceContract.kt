package com.fakturku.aplikasi.ui.activity.settingInvoice

interface SettingInvoiceContract{

    interface View{
        fun showSetingInvoiceSuccess()
    }

    interface Presenter{
        fun settingInvoice()
    }
}
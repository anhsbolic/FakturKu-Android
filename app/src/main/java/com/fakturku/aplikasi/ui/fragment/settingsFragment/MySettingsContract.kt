package com.fakturku.aplikasi.ui.fragment.settingsFragment

interface MySettingsContract{

    interface View{
        fun showAccountSettings()

        fun showInvoiceSettings()
    }

    interface Presenter{
        fun settingAccount()

        fun settingInvoice()
    }
}
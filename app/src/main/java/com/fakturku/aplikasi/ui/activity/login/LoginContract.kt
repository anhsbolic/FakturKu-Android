package com.fakturku.aplikasi.ui.activity.login

import android.content.Context

interface LoginContract{
    interface View{
        fun showErrorInput(isEmailValidate: Boolean, isPasswordValidate: Boolean)

        fun showProgress()

        fun hideProgress()

        fun goToRegister()

        fun goToDashboard()
    }

    interface Presenter{
        fun login(context: Context, email: String, pass: String)

        fun register()
    }
}
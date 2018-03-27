package com.fakturku.aplikasi.ui.activity.login

interface LoginContract{
    interface View{
        fun showErrorInput(isEmailValidate: Boolean, isPasswordValidate: Boolean)

        fun showProgress()

        fun hideProgress()

        fun goToRegister()

        fun goToDashboard()
    }

    interface Presenter{
        fun validateUser(email: String, pass: String)

        fun login(email: String, pass: String)

        fun register()
    }
}
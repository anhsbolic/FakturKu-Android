package com.fakturku.aplikasi.ui.activity.register

interface RegisterContract{
    interface View{
        fun showErrorInput(isNameValidate: Boolean,
                           isEmailValidate: Boolean,
                           isPasswordValidate: Boolean,
                           isConfirmPasswordValidate: Boolean)

        fun showErrorPasswordNotSame(isPasswordSame: Boolean)

        fun showProgress()

        fun hideProgress()

        fun successToLogin(email: String)

        fun backToLogin()

    }

    interface Presenter{
        fun validateInput(name: String, email: String, pass: String, confirmPass: String)

        fun register(name: String, email: String, pass: String, confirmPass: String)

        fun login(email: String)

        fun backLogin()

    }
}
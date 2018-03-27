package com.fakturku.aplikasi.ui.activity.register

import android.os.Handler

class RegisterPresenter(private val view: RegisterContract.View): RegisterContract.Presenter{
    override fun register(name: String, email: String, pass: String, confirmPass: String) {
        validateInput(name, email, pass, confirmPass)
    }

    override fun validateInput(name: String, email: String, pass: String, confirmPass: String) {
        var isNameValidate = false
        var isEmailValidate = false
        var isPasswordValidate = false
        var isConfirmPasswordValidate = false

        if(name.isNotEmpty()){
            isNameValidate = true
        }

        if(email.isNotEmpty()){
            val isInEmailFormat = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
            if (isInEmailFormat) {
                isEmailValidate = true
            }
        }

        if(pass.isNotEmpty()){
            isPasswordValidate = true
        }

        if(confirmPass.isNotEmpty()){
            isConfirmPasswordValidate = true
        }

        if (isNameValidate
                && isEmailValidate
                && isPasswordValidate
                && isConfirmPasswordValidate){

            if (pass == confirmPass) {
                view.showErrorPasswordNotSame(true)
                view.showProgress()
                Handler().postDelayed({
                    view.hideProgress()
                    Handler().postDelayed({
                        login(email)
                    },30)
                },1200)
            } else {
                view.showErrorPasswordNotSame(false)
            }
        } else {
            view.showErrorInput(
                    isNameValidate,
                    isEmailValidate,
                    isPasswordValidate,
                    isConfirmPasswordValidate)
        }
    }


    override fun login(email: String) {
        view.successToLogin(email)
    }

    override fun backLogin() {
        view.backToLogin()
    }

}
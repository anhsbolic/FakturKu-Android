package com.fakturku.aplikasi.ui.activity.login

import android.os.Handler


class LoginPresenter(private val view: LoginContract.View): LoginContract.Presenter{

    override fun login(email: String, pass: String) {
        validateUser(email, pass)
    }

    override fun validateUser(email: String, pass: String) {
        var isEmailValidate = false
        var isPasswordValidate = false

        if(email.isNotEmpty()){
            val isInEmailFormat = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
            if (isInEmailFormat) {
                isEmailValidate = true
            }
        }

        if(pass.isNotEmpty()){
            isPasswordValidate = true
        }

        if (isEmailValidate && isPasswordValidate){
            view.showProgress()
            Handler().postDelayed({
                view.hideProgress()

                Handler().postDelayed({
                    view.goToDashboard()
                },30)
            },1200)
        }else{
            view.showErrorInput(isEmailValidate, isPasswordValidate)
        }
    }

    override fun register() {
        view.goToRegister()
    }
}
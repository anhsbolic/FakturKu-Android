package com.fakturku.aplikasi.ui.activity.login

import android.os.Handler
import android.content.Context
import android.content.Context.MODE_PRIVATE

class LoginPresenter(private val view: LoginContract.View): LoginContract.Presenter{

    override fun login(context: Context, email: String, pass: String) {
        validateUser(context, email, pass)
    }

    private fun validateUser(context: Context, email: String, pass: String) {
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

            saveLoginToken(context, email)

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

    private fun saveLoginToken(context:Context, token: String) {
        val pref = context.getSharedPreferences("LoginPref", MODE_PRIVATE)
        pref.edit().putString("LoginToken", token).apply()
    }

    override fun register() {
        view.goToRegister()
    }
}
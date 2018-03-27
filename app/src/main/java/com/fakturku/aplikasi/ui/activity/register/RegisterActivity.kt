package com.fakturku.aplikasi.ui.activity.register

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.ui.activity.login.LoginActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterContract.View {
    private lateinit var presenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //Init Presenter
        presenter = RegisterPresenter(this@RegisterActivity)

        //UI listener
        registerBtnRegister.setOnClickListener { register() }
        registerBtnLogin.setOnClickListener { presenter.backLogin() }
    }


    //Private Function
    private fun register() {
        val name = registerEtName.text.toString()
        val email = registerEtEmail.text.toString()
        val pass = registerEtPassword.text.toString()
        val confirmPass = registerEtConfirmPassword.text.toString()

        presenter.register(name, email, pass, confirmPass)
    }

    //View Function
    override fun showErrorInput(isNameValidate: Boolean, isEmailValidate: Boolean, isPasswordValidate: Boolean, isConfirmPasswordValidate: Boolean) {
        if(!isNameValidate){
            registerEtName.error = "isi dengan nama lengkap Anda"
        }else{
            registerEtName.error = null
        }

        if(!isEmailValidate){
            registerEtEmail.error = "isi dengan email personal Anda"
        }else{
            registerEtEmail.error = null
        }

        if(!isPasswordValidate){
            registerEtPassword.error = "isi dengan password"
        }else{
            registerEtPassword.error = null
        }

        if(!isConfirmPasswordValidate){
            registerEtConfirmPassword.error = "isi dengan password yg sama"
        }else{
            registerEtConfirmPassword.error = null
        }
    }

    override fun showErrorPasswordNotSame(isPasswordSame: Boolean) {
        if (!isPasswordSame) {
            registerEtConfirmPassword.error = "password tidak sama"
        } else {
            registerEtConfirmPassword.error = null
        }
    }

    override fun showProgress() {
        registerEtName.isEnabled = false
        registerEtEmail.isEnabled = false
        registerEtPassword.isEnabled = false
        registerEtConfirmPassword.isEnabled = false
        registerBtnRegister.isEnabled = false
        registerBtnLogin.isEnabled = false
        registerCardProgress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        registerEtName.isEnabled = true
        registerEtEmail.isEnabled = true
        registerEtPassword.isEnabled = true
        registerEtConfirmPassword.isEnabled = true
        registerBtnRegister.isEnabled = true
        registerBtnLogin.isEnabled = true
        registerCardProgress.visibility = View.GONE
    }

    override fun successToLogin(email: String) {
        val intentLogin = Intent()
        intentLogin.putExtra(LoginActivity.INTENT_EMAIL, email)
        setResult(LoginActivity.INTENT_REGISTER_SUCCESS, intentLogin)
        finish()
    }

    override fun backToLogin() {
        onBackPressed()
    }

}

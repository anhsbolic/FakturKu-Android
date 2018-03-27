package com.fakturku.aplikasi.ui.activity.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.ui.activity.DashboardActivity
import com.fakturku.aplikasi.ui.activity.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var presenter : LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this@LoginActivity)

        loginBtnLogin.setOnClickListener { login() }

        loginBtnRegister.setOnClickListener { presenter.register() }
    }

    //Private Function
    private fun login() {
        val email = loginEtEmail.text.toString()
        val pass = loginEtPassword.text.toString()
        presenter.login(email, pass)
    }

    //Presenter Function
    override fun showErrorInput(isEmailValidate: Boolean, isPasswordValidate: Boolean) {
        if(!isEmailValidate){
            loginEtEmail.error = "isi dengan email Anda"
        }else{
            loginEtEmail.error = null
        }

        if(!isPasswordValidate){
            loginEtPassword.error = "isi dengan password akun Anda"
        }else{
            loginEtPassword.error = null
        }
    }

    override fun showProgress() {
        loginEtEmail.isEnabled = false
        loginEtPassword.isEnabled = false
        loginBtnLogin.isEnabled = false
        loginBtnRegister.isEnabled = false
        loginCardProgress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        loginEtEmail.isEnabled = true
        loginEtPassword.isEnabled = true
        loginBtnLogin.isEnabled = true
        loginBtnRegister.isEnabled = true
        loginCardProgress.visibility = View.GONE
    }

    override fun goToRegister() {
        val intentRegister = Intent(this@LoginActivity, RegisterActivity::class.java)
        startActivity(intentRegister)
    }

    override fun goToDashboard() {
        val intentDashboard = Intent(this@LoginActivity, DashboardActivity::class.java)
        startActivity(intentDashboard)
        finish()
    }
}

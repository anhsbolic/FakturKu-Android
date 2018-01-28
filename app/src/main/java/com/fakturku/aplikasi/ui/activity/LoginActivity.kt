package com.fakturku.aplikasi.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fakturku.aplikasi.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        loginBtnLoginByFacebook.setOnClickListener { loginByFacebookId() }
        loginBtnLoginByGoogle.setOnClickListener { loginByGoogleId() }
    }

    private fun loginByFacebookId() {
        //TODO : login by facebook login SDK implementation

        val intentDashboard = Intent(this@LoginActivity, DashboardActivity::class.java)
        startActivity(intentDashboard)
        finish()
    }

    private fun loginByGoogleId() {
        //TODO : login by google login SDK implementation

        val intentDashboard = Intent(this@LoginActivity, DashboardActivity::class.java)
        startActivity(intentDashboard)
        finish()
    }
}

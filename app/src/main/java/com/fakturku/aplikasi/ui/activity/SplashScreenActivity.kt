package com.fakturku.aplikasi.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.ui.activity.login.LoginActivity

class SplashScreenActivity : AppCompatActivity() {

    private val longSplashTime: Long = 1800

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            if(isLogin()){
                goToDashboard()
            }else{
                goToLogin()
            }
        }, longSplashTime)
    }

    private fun isLogin(): Boolean {
        var isLogin = false

        val pref = applicationContext.getSharedPreferences("LoginPref", MODE_PRIVATE)
        if (pref.contains("LoginToken")) {
            isLogin = true
        }

        return isLogin
    }

    private fun goToLogin() {
        val intentLogin = Intent(this@SplashScreenActivity, LoginActivity::class.java)
        startActivity(intentLogin)
        finish()
    }

    private fun goToDashboard() {
        val intentDashboard = Intent(this@SplashScreenActivity, DashboardActivity::class.java)
        startActivity(intentDashboard)
        finish()
    }
}

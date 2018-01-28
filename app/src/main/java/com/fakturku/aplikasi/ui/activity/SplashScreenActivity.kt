package com.fakturku.aplikasi.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.fakturku.aplikasi.R

class SplashScreenActivity : AppCompatActivity() {

    private val longSplashTime: Long = 3000

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
        return false
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

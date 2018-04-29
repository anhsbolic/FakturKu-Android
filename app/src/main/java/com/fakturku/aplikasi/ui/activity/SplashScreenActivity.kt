package com.fakturku.aplikasi.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.model.User
import com.fakturku.aplikasi.ui.activity.login.LoginActivity

class SplashScreenActivity : AppCompatActivity() {

    private val longSplashTime: Long = 1800

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            if(isLogin()){
                val user = User(
                        1,
                        "Anhar Solehudin",
                        "+12345678910",
                        "anharsolehudin@gmail.com",
                        null,
                        null,
                        null,
                        "Tani Sejahtera",
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null)
                goToDashboard(user)
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

    private fun goToDashboard(user: User) {
        val intentDashboard = Intent(this@SplashScreenActivity, DashboardActivity::class.java)
        intentDashboard.putExtra(DashboardActivity.INTENT_USER_DATA, user)
        startActivity(intentDashboard)
        finish()
    }
}

package com.fakturku.aplikasi.ui.activity.settingAccount

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.model.User
import kotlinx.android.synthetic.main.activity_setting_account.*

class SettingAccountActivity : AppCompatActivity() {
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_account)

        //get user data
        if (intent.hasExtra(INTENT_USER_DATA)) {
            user = intent.getParcelableExtra(INTENT_USER_DATA)
            settingAccountTxtName.text = user.name
            settingAccountTxtEmail.text = user.email
        }

        val name = user.name!!
        val initialName = name[0].toString()
        val colorGenerator: ColorGenerator = ColorGenerator.MATERIAL
        val color: Int = colorGenerator.getColor(initialName)
        val textDrawable: TextDrawable = TextDrawable.builder().beginConfig()
                .width(100)
                .height(100)
                .endConfig()
                .buildRound(initialName, color)
       settingAccountImgUser.setImageDrawable(textDrawable)
    }

    companion object {
        const val INTENT_USER_DATA : String = "IntentUserData"
    }

}

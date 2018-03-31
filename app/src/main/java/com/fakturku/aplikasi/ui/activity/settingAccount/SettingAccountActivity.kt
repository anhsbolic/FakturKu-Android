package com.fakturku.aplikasi.ui.activity.settingAccount

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.fakturku.aplikasi.R
import kotlinx.android.synthetic.main.activity_setting_account.*

class SettingAccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_account)

        val name = "Hilman Firdaus"
        settingAccountTxtName.text = name

        val email = "hilmanfirdaus@gmail.com"
        settingAccountTxtEmail.text = email

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

}

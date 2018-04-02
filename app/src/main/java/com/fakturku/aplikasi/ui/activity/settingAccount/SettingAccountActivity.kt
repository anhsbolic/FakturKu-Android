package com.fakturku.aplikasi.ui.activity.settingAccount

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.model.User
import kotlinx.android.synthetic.main.activity_setting_account.*

class SettingAccountActivity : AppCompatActivity(), SettingAccountContract.View {

    private lateinit var presenter: SettingAccountPresenter

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_account)

        //Init Presenter
        presenter = SettingAccountPresenter(this@SettingAccountActivity)

        //get user data
        if (intent.hasExtra(INTENT_USER_DATA)) {
            user = intent.getParcelableExtra(INTENT_USER_DATA)

            presenter.setDataToUI(user)
        }

        //Fab Handling & listener
        settingAccountFabEdit.setOnClickListener { presenter.editUser(user) }
    }

    override fun setDataToUI(user: User) {
        //Personal
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

        settingAccountTxtName.text = name

        settingAccountTxtEmail.text = user.email

        if (user.phone != null) {
            settingAccountTxtPhone.text = user.phone
        }

        if (user.address != null) {
            settingAccountTxtAddress.text = user.address
        }

        if (user.city != null) {
            settingAccountTxtCity.text = user.city
        }

        //Company
        if (user.company_name != null) {
            settingAccountTxtCompanyName.text = user.company_name
        }

        if (user.company_email != null) {
            settingAccountTxtCompanyEmail.text = user.company_email
        }

        if (user.company_phone != null) {
            settingAccountTxtCompanyPhone.text = user.company_phone
        }

        if (user.company_address != null) {
            settingAccountTxtCompanyAddress.text = user.company_address
        }

        if (user.company_city != null) {
            settingAccountTxtCompanyCity.text = user.company_city
        }
    }

    override fun showEditAccount(user: User) {
        Toast.makeText(this@SettingAccountActivity, "EDIT", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val INTENT_USER_DATA : String = "IntentUserData"
    }

}

package com.fakturku.aplikasi.ui.activity.settingAccountForm

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.model.User
import com.fakturku.aplikasi.ui.activity.settingAccount.SettingAccountActivity
import kotlinx.android.synthetic.main.activity_setting_account_form.*

class SettingAccountFormActivity : AppCompatActivity(), SettingAccountFormContract.View {

    private lateinit var presenter: SettingAccountFormPresenter
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_account_form)
        title = null
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_clear)

        //Init Presenter
        presenter = SettingAccountFormPresenter(this@SettingAccountFormActivity)

        //Get User Data
        if (intent.hasExtra(INTENT_USER_DATA)) {
            user = intent.getParcelableExtra(INTENT_USER_DATA)
            presenter.setUserData(user)
        }

        //UI handling & listener
        accountFormBtnSave.setOnClickListener { updateData() }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                onBackPressed()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this@SettingAccountFormActivity)
                .setMessage(R.string.account_form_backed_message)
                .setPositiveButton(R.string.account_form_backed_positive,{ _ , _ ->
                    super.onBackPressed()
                })
                .setNegativeButton(R.string.account_form_backed_negative, null)
                .show()
    }

    override fun setUserDataToUI(user: User) {
        accountFormName.setText(user.name)
        accountFormPhone.setText(user.phone)
        accountFormEmail.setText(user.email)
        accountFormEmail.isEnabled = false
        accountFormAddress.setText(user.address)
        accountFormCity.setText(user.city)
        accountFormCompanyName.setText(user.company_name)
        accountFormCompanyPhone.setText(user.company_phone)
        accountFormCompanyEmail.setText(user.company_email)
        accountFormCompanyAddress.setText(user.company_address)
        accountFormCompanyCity.setText(user.company_city)
    }

    private fun updateData() {
        val id = user.id
        var name : String? = null
        var phone : String? = null
        val email = user.email
        var address : String? = null
        var city : String? = null
        var accountNumber : String? = null
        var companyName : String? = null
        var companyPhone : String? = null
        var companyEmail : String? = null
        var companyAddress : String? = null
        var companyCity : String? = null
        var companyAccountNumber : String? = null
        val createdDate = user.created_date
        var updatedDate : String? = null

        if (accountFormName.text.toString().trim().isNotEmpty()) {
            name = accountFormName.text.toString()
        }
        if (accountFormPhone.text.toString().trim().isNotEmpty()) {
            phone  = accountFormPhone.text.toString()
        }
        if (accountFormAddress.text.toString().trim().isNotEmpty()) {
            address = accountFormAddress.text.toString()
        }
        if (accountFormCity.text.toString().trim().isNotEmpty()) {
            city = accountFormCity.text.toString()
        }
        if (accountFormCompanyName.text.toString().trim().isNotEmpty()) {
            companyName = accountFormCompanyName.text.toString()
        }
        if (accountFormCompanyEmail.text.toString().trim().isNotEmpty()) {
            companyEmail = accountFormCompanyEmail.text.toString()
        }
        if (accountFormCompanyPhone.text.toString().trim().isNotEmpty()) {
            companyPhone  = accountFormCompanyPhone.text.toString()
        }
        if (accountFormCompanyAddress.text.toString().trim().isNotEmpty()) {
            companyAddress = accountFormCompanyAddress.text.toString()
        }
        if (accountFormCompanyCity.text.toString().trim().isNotEmpty()) {
            companyCity = accountFormCompanyCity.text.toString()
        }

        presenter.saveUpdateUserData(
                id,
                name,
                phone,
                email,
                address,
                city,
                accountNumber,
                companyName,
                companyPhone,
                companyEmail,
                companyAddress,
                companyCity,
                companyAccountNumber,
                createdDate,
                updatedDate)
    }

    override fun showErrorEtCompanyEmail(isValid: Boolean) {
        if (!isValid){
            accountFormCompanyEmail.error = "Isi dengan email perusahaan"
        } else {
            accountFormCompanyEmail.error = null
        }
    }

    override fun showUpdateDataSuccess(user: User) {
        val msg = "Profil berhasil diupdate"
        Snackbar.make(accountFormCoordinatorLayout, msg, Snackbar.LENGTH_SHORT).show()

        Handler().postDelayed({
            val intentAddCostumerSuccess = Intent()
            intentAddCostumerSuccess.putExtra(SettingAccountActivity.INTENT_UPDATE_CODE_SUCCESS_DATA, user)
            setResult(SettingAccountActivity.INTENT_UPDATE_CODE_SUCCESS, intentAddCostumerSuccess)
            finish()
        }, 1200)
    }

    companion object {
        const val INTENT_USER_DATA : String = "IntentUserData"
    }
}

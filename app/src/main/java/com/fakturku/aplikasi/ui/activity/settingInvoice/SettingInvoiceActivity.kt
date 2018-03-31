package com.fakturku.aplikasi.ui.activity.settingInvoice

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.ui.fragment.settingsFragment.MySettingsFragment
import kotlinx.android.synthetic.main.activity_setting_invoice.*

class SettingInvoiceActivity : AppCompatActivity(), SettingInvoiceContract.View {

    private lateinit var presenter: SettingInvoicePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_invoice)
        title = null
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_clear)

        //Init Presenter
        presenter = SettingInvoicePresenter(this@SettingInvoiceActivity)

        //UI Handling & listener
        ///Edit Text Length Control
        val totalLengthEtPaymentInstruction = "/50"
        var lengthEtPaymentInstruction = settingInvoiceEtPaymentInstruction.length()
        var txtLengthEtPaymentInstruction = "$lengthEtPaymentInstruction$totalLengthEtPaymentInstruction"
        settingInvoiceTxtPaymentInstructionLength.text = txtLengthEtPaymentInstruction
        settingInvoiceEtPaymentInstruction.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                lengthEtPaymentInstruction = settingInvoiceEtPaymentInstruction.length()
                txtLengthEtPaymentInstruction = "$lengthEtPaymentInstruction$totalLengthEtPaymentInstruction"
                settingInvoiceTxtPaymentInstructionLength.text = txtLengthEtPaymentInstruction
            }
        })

        val totalLengthEtPaymentChoice = "/120"
        var lengthEtPaymentChoice = settingInvoiceEtPaymentChoice.length()
        var txtLengthEtPaymentChoice = "$lengthEtPaymentChoice$totalLengthEtPaymentChoice"
        settingInvoiceTxtPaymentChoiceLength.text = txtLengthEtPaymentChoice
        settingInvoiceEtPaymentChoice.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                lengthEtPaymentChoice = settingInvoiceEtPaymentChoice.length()
                txtLengthEtPaymentChoice = "$lengthEtPaymentChoice$totalLengthEtPaymentChoice"
                settingInvoiceTxtPaymentChoiceLength.text = txtLengthEtPaymentChoice
            }
        })

        val totalLengthEtCompanyAddress = "/50"
        var lengthEtCompanyAddress = settingInvoiceEtCompanyAddress.length()
        var txtLengthEtCompanyAddress = "$lengthEtCompanyAddress$totalLengthEtCompanyAddress"
        settingInvoiceTxtCompanyAddressLength.text = txtLengthEtCompanyAddress
        settingInvoiceEtCompanyAddress.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                lengthEtCompanyAddress = settingInvoiceEtCompanyAddress.length()
                txtLengthEtCompanyAddress = "$lengthEtCompanyAddress$totalLengthEtCompanyAddress"
                settingInvoiceTxtCompanyAddressLength.text = txtLengthEtCompanyAddress
            }
        })

        ///Save Button
        settingInvoiceBtnSave.setOnClickListener { presenter.settingInvoice() }
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
        AlertDialog.Builder(this@SettingInvoiceActivity)
                .setMessage(R.string.setting_invoice_backed_message)
                .setPositiveButton(R.string.setting_invoice_backed_positive,{ _ , _ ->
                    super.onBackPressed()
                })
                .setNegativeButton(R.string.setting_invoice_backed_negative, null)
                .show()
    }

    override fun showSetingInvoiceSuccess() {
        val msg = "Pengaturan faktur berhasil"
        Snackbar.make(settingInvoiceCoordinatorLayout, msg, Snackbar.LENGTH_SHORT).show()

        Handler().postDelayed({
            val intentSettingInvoiceSuccess = Intent()
            setResult(MySettingsFragment.INTENT_SET_INVOICE_SUCCESS, intentSettingInvoiceSuccess)
            finish()
        }, 1200)
    }
}

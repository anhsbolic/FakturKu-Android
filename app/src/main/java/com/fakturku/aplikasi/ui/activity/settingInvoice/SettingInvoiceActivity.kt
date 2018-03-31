package com.fakturku.aplikasi.ui.activity.settingInvoice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import com.fakturku.aplikasi.R

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
}

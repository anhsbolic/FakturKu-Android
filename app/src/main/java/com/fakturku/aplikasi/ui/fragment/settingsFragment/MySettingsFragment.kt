package com.fakturku.aplikasi.ui.fragment.settingsFragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.ui.activity.settingInvoice.SettingInvoiceActivity
import kotlinx.android.synthetic.main.fragment_settings.*

class MySettingsFragment : Fragment(), MySettingsContract.View {

    private lateinit var presenter: MySettingsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Init Presenter
        presenter = MySettingsPresenter(this@MySettingsFragment)

        //UI Handling
        mySettingsAccount.setOnClickListener { presenter.settingAccount() }
        mySettingsInvoice.setOnClickListener { presenter.settingInvoice() }
    }

    override fun showAccountSettings() {
        Toast.makeText(activity, "SET AKUN", Toast.LENGTH_SHORT).show()
    }

    override fun showInvoiceSettings() {
        val intentSetInvoice = Intent(activity, SettingInvoiceActivity::class.java)
        startActivityForResult(intentSetInvoice, INTENT_SET_INVOICE_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            INTENT_SET_INVOICE_CODE->{
                when(resultCode){
                    INTENT_SET_INVOICE_SUCCESS->{
                        Toast.makeText(activity,"INVOICE SETTINGS UPDATED",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else->{ super.onActivityResult(requestCode, resultCode, data) }
        }

    }

    companion object {
        const val INTENT_SET_INVOICE_CODE = 20
        const val INTENT_SET_INVOICE_SUCCESS = 21

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                MySettingsFragment().apply {
                    arguments = Bundle().apply {
//                        putString(ARG_PARAM2, param2)
//                        putString(ARG_PARAM1, param1)
                    }
                }
    }
}

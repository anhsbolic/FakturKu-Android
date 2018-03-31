package com.fakturku.aplikasi.ui.fragment.settingsFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.fakturku.aplikasi.R
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
        Toast.makeText(activity, "SET FAKTUR", Toast.LENGTH_SHORT).show()
    }

    companion object {
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

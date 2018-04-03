package com.fakturku.aplikasi.ui.fragment.invoiceFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast

import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.ui.activity.DashboardActivity
import kotlinx.android.synthetic.main.fragment_invoice.*

class InvoiceFragment : Fragment(), InvoiceContract.View {

    private lateinit var presenter: InvoicePresenter

    private var isFragmentWasVisited: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if(isFragmentWasVisited){
            (activity as DashboardActivity).setTitleBar(R.string.invoice_title)
        }else{
            isFragmentWasVisited = true
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_invoice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Init Presenter
        presenter = InvoicePresenter(this@InvoiceFragment)

        //Init Animation
        val animFabShowButton = AnimationUtils.loadAnimation(activity as DashboardActivity, R.anim.fab_show_button)
        val animFabHideButton = AnimationUtils.loadAnimation(activity as DashboardActivity, R.anim.fab_hide_button)
        val animFabLayoutShowButton = AnimationUtils.loadAnimation(activity as DashboardActivity, R.anim.fab_show_layout)
        val animFabLayoutHideButton = AnimationUtils.loadAnimation(activity as DashboardActivity, R.anim.fab_hide_layout)

        //Fab Handling
        invoiceFab.setOnClickListener {
            if (invoiceLayoutFabBuy.visibility == View.GONE && invoiceLayoutFabSell.visibility == View.GONE ) {
                invoiceLayoutFabBuy.visibility = View.VISIBLE
                invoiceLayoutFabSell.visibility = View.VISIBLE
                invoiceFab.startAnimation(animFabShowButton)
                invoiceLayoutFabSell.startAnimation(animFabLayoutShowButton)
                invoiceLayoutFabBuy.startAnimation(animFabLayoutShowButton)
            } else{
                invoiceLayoutFabBuy.visibility = View.GONE
                invoiceLayoutFabSell.visibility = View.GONE
                invoiceFab.startAnimation(animFabHideButton)
                invoiceLayoutFabSell.startAnimation(animFabLayoutHideButton)
                invoiceLayoutFabBuy.startAnimation(animFabLayoutHideButton)
            }
        }

        invoiceFabBuy.setOnClickListener {
            invoiceLayoutFabBuy.visibility = View.GONE
            invoiceLayoutFabSell.visibility = View.GONE
            invoiceFab.startAnimation(animFabHideButton)
            invoiceLayoutFabSell.startAnimation(animFabLayoutHideButton)
            invoiceLayoutFabBuy.startAnimation(animFabLayoutHideButton)
            Toast.makeText(activity, "FAKTUR BELI", Toast.LENGTH_SHORT).show()
        }

        invoiceFabSell.setOnClickListener {
            invoiceLayoutFabBuy.visibility = View.GONE
            invoiceLayoutFabSell.visibility = View.GONE
            invoiceFab.startAnimation(animFabHideButton)
            invoiceLayoutFabSell.startAnimation(animFabLayoutHideButton)
            invoiceLayoutFabBuy.startAnimation(animFabLayoutHideButton)
            Toast.makeText(activity, "FAKTUR JUAL", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {

        fun newInstance(param1: String, param2: String): InvoiceFragment {
            val fragment = InvoiceFragment()
            val args = Bundle()
//            args.putString(ARG_PARAM1, param1)
//            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}

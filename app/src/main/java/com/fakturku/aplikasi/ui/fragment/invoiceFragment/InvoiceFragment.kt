package com.fakturku.aplikasi.ui.fragment.invoiceFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast

import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.ui.activity.DashboardActivity
import kotlinx.android.synthetic.main.fragment_invoice.*

class InvoiceFragment : Fragment(), InvoiceContract.View {

    private lateinit var presenter: InvoicePresenter
    private lateinit var animFabShowButton : Animation
    private lateinit var animFabHideButton : Animation
    private lateinit var animFabLayoutShowButton : Animation
    private lateinit var animFabLayoutHideButton : Animation

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
        animFabShowButton = AnimationUtils.loadAnimation(activity as DashboardActivity,
                R.anim.fab_show_button)
        animFabHideButton = AnimationUtils.loadAnimation(activity as DashboardActivity,
                R.anim.fab_hide_button)
        animFabLayoutShowButton = AnimationUtils.loadAnimation(activity as DashboardActivity,
                R.anim.fab_show_layout)
        animFabLayoutHideButton = AnimationUtils.loadAnimation(activity as DashboardActivity,
                R.anim.fab_hide_layout)

        //Fab Handling
        invoiceFab.setOnClickListener {
            if (invoiceLayoutFabBuy.visibility == View.GONE && invoiceLayoutFabSell.visibility == View.GONE ) {
                showFabSubmenu()
            } else{
                hideFabSubMenu()
            }
        }

        invoiceFabSell.setOnClickListener {
            hideFabSubMenu()
            presenter.addSalesInvoice()
        }

        invoiceFabBuy.setOnClickListener {
            hideFabSubMenu()
            presenter.addPurchaseInvoice()
        }

        invoiceFabCost.setOnClickListener {
            hideFabSubMenu()
            presenter.addCostInvoice()
        }

    }

    override fun showFabSubmenu() {
        invoiceLayoutFabBuy.visibility = View.VISIBLE
        invoiceLayoutFabSell.visibility = View.VISIBLE
        invoiceLayoutFabCost.visibility = View.VISIBLE
        invoiceFab.startAnimation(animFabShowButton)
        invoiceLayoutFabSell.startAnimation(animFabLayoutShowButton)
        invoiceLayoutFabBuy.startAnimation(animFabLayoutShowButton)
        invoiceLayoutFabCost.startAnimation(animFabLayoutShowButton)
    }

    override fun hideFabSubMenu() {
        invoiceLayoutFabBuy.visibility = View.GONE
        invoiceLayoutFabSell.visibility = View.GONE
        invoiceLayoutFabCost.visibility = View.GONE
        invoiceFab.startAnimation(animFabHideButton)
        invoiceLayoutFabSell.startAnimation(animFabLayoutHideButton)
        invoiceLayoutFabBuy.startAnimation(animFabLayoutHideButton)
        invoiceLayoutFabCost.startAnimation(animFabLayoutHideButton)
    }

    override fun openAddSalesInvoice() {
        Toast.makeText(activity, "FAKTUR PENJUALAN", Toast.LENGTH_SHORT).show()
    }

    override fun openAddPurchaseInvoice() {
        Toast.makeText(activity, "FAKTUR PEMBELIAN", Toast.LENGTH_SHORT).show()
    }

    override fun openAddCostInvoice() {
        Toast.makeText(activity, "FAKTUR BIAYA", Toast.LENGTH_SHORT).show()
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

package com.fakturku.aplikasi.ui.fragment.invoiceFragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast

import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.model.Invoice
import com.fakturku.aplikasi.ui.activity.DashboardActivity
import com.fakturku.aplikasi.ui.activity.invoiceDetails.InvoiceDetailsActivity
import com.fakturku.aplikasi.ui.adapter.InvoiceListAdapter
import kotlinx.android.synthetic.main.fragment_invoice.*

class InvoiceFragment : Fragment(), InvoiceContract.View {

    private lateinit var presenter: InvoicePresenter
    private lateinit var animFabShowButton : Animation
    private lateinit var animFabHideButton : Animation
    private lateinit var animFabLayoutShowButton : Animation
    private lateinit var animFabLayoutHideButton : Animation

    private var isFragmentWasVisited: Boolean = false

    private var dataInvoiceList: MutableList<Invoice> = ArrayList()
    private lateinit var adapterRvInvoiceList: RecyclerView.Adapter<*>
    private lateinit var lmRvInvoiceList: LinearLayoutManager
    private lateinit var animator: DefaultItemAnimator
    private lateinit var dividerItemDecoration: DividerItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

        //Init RecyclerView
        initRecyclerView()

        //Load Init Data
        presenter.loadInvoiceListData(1)

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

    override fun onCreateOptionsMenu(menu: Menu?, menuInflater: MenuInflater?) {
        menuInflater!!.inflate(R.menu.fragment_invoice_menu, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){

            R.id.invoiceMenuAll->{
                if (dataInvoiceList.isNotEmpty()) {
                    dataInvoiceList.clear()
                    adapterRvInvoiceList.notifyDataSetChanged()
                }

                presenter.loadInvoiceListData(1)
                return true
            }

            R.id.invoiceMenuDebt->{
                if (dataInvoiceList.isNotEmpty()) {
                    dataInvoiceList.clear()
                    adapterRvInvoiceList.notifyDataSetChanged()
                }

                presenter.loadInvoiceDebtListData(1)
                return true
            }

            R.id.invoiceMenuPaid->{
                if (dataInvoiceList.isNotEmpty()) {
                    dataInvoiceList.clear()
                    adapterRvInvoiceList.notifyDataSetChanged()
                }

                presenter.loadInvoicePaidListData(1)
                return true
            }

            R.id.invoiceMenuDraft->{
                if (dataInvoiceList.isNotEmpty()) {
                    dataInvoiceList.clear()
                    adapterRvInvoiceList.notifyDataSetChanged()
                }

                presenter.loadInvoiceDraftListData(1)
                return true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun initRecyclerView() {
        adapterRvInvoiceList = InvoiceListAdapter(dataInvoiceList, object : InvoiceListAdapter.OnItemClickListener{
            override fun onItemClick(invoice: Invoice) {
                presenter.seeInvoiceDetails(invoice)
            }
        })
        lmRvInvoiceList = LinearLayoutManager(activity)
        animator = DefaultItemAnimator()
        animator.addDuration = 300
        animator.removeDuration = 600

        dividerItemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        invoiceRv.adapter = adapterRvInvoiceList
        invoiceRv.layoutManager = lmRvInvoiceList
        invoiceRv.itemAnimator = animator
        invoiceRv.addItemDecoration(dividerItemDecoration)
        invoiceRv.setHasFixedSize(true)
/*
        val colorPrimaryDark = ContextCompat.getColor(activity as DashboardActivity, R.color.colorPrimaryDark)
        costumerListSwipeRefreshLayout.setColorSchemeColors(colorPrimaryDark)
        costumerListSwipeRefreshLayout.setOnRefreshListener {
            if (!isLoadingData){
                if((activity as DashboardActivity).isNetworkAvailable()){
                    loadThenSetMosqueData(lastDataTime)
                }else{
                    addMosqueSwipeRefreshLayout.isRefreshing = false
                    showSnackBar("Periksa Koneksi Internet Anda ...",
                            Snackbar.LENGTH_LONG,300)
                }
            }
        }
*/
    }

    override fun showProgress() {
        invoiceProgressLayout.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        invoiceProgressLayout.visibility = View.GONE
    }

    override fun showPlaceholder() {
        invoiceImgPlaceHolder.visibility = View.VISIBLE
    }

    override fun hidePlaceholder() {
        invoiceImgPlaceHolder.visibility = View.GONE
    }

    override fun showInvoiceList(invoiceList: MutableList<Invoice>) {
        if (invoiceRv != null){
            for (i in 0 until invoiceList.size){
                dataInvoiceList.add(invoiceList[i])
                val index = dataInvoiceList.lastIndex
                adapterRvInvoiceList.notifyItemInserted(index)

                if (index == 0){
                    invoiceSwipeRefreshLayout.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun showInvoiceDetails(invoice: Invoice) {
        val intentInvoiceDetails = Intent(activity, InvoiceDetailsActivity::class.java)
        intentInvoiceDetails.putExtra(InvoiceDetailsActivity.INTENT_DATA_INVOICE, invoice)
        startActivityForResult(intentInvoiceDetails, INTENT_INVOICE_DETAILS_CODE)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            INTENT_INVOICE_DETAILS_CODE->{
                when(resultCode){
                    INTENT_INVOICE_DETAILS_UPDATE->{

                    }
                }
            }
            else->{ super.onActivityResult(requestCode, resultCode, data) }
        }

    }

    companion object {
        const val INTENT_INVOICE_DETAILS_CODE = 20
        const val INTENT_INVOICE_DETAILS_UPDATE = 21

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

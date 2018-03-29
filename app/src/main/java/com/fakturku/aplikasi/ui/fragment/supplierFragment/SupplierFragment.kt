package com.fakturku.aplikasi.ui.fragment.supplierFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.model.Supplier
import com.fakturku.aplikasi.ui.activity.DashboardActivity
import com.fakturku.aplikasi.ui.adapter.SupplierListAdapter
import kotlinx.android.synthetic.main.fragment_supplier.*
import java.util.ArrayList

class SupplierFragment : Fragment(), SupplierContract.View {

    private lateinit var presenter: SupplierPresenter

    private var isFragmentWasVisited: Boolean = false

    private var dataSupplierList: MutableList<Supplier> = ArrayList()
    private lateinit var adapterRvSupplierList: RecyclerView.Adapter<*>
    private lateinit var lmRvSupplierList: LinearLayoutManager
    private lateinit var animator: DefaultItemAnimator
    private lateinit var dividerItemDecoration: DividerItemDecoration

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if(isFragmentWasVisited){
            (activity as DashboardActivity).setTitleBar(R.string.supplier_title)
        }else{
            isFragmentWasVisited = true
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_supplier, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Init Presenter
        presenter = SupplierPresenter(this@SupplierFragment)

        //Init RecyclerView
        initRecyclerView()

        //Load Init Data
        presenter.loadSupplierListData(1)

    }

    override fun initRecyclerView() {
        adapterRvSupplierList = SupplierListAdapter(dataSupplierList, object : SupplierListAdapter.OnItemClickListener{
            override fun onItemClick(supplier: Supplier) {
                presenter.seeSupplierDetails(supplier)
            }
        })
        lmRvSupplierList = LinearLayoutManager(activity)
        animator = DefaultItemAnimator()
        animator.addDuration = 300
        animator.removeDuration = 600

        dividerItemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        supplierRv.adapter = adapterRvSupplierList
        supplierRv.layoutManager = lmRvSupplierList
        supplierRv.itemAnimator = animator
        supplierRv.addItemDecoration(dividerItemDecoration)
        supplierRv.setHasFixedSize(true)
    }

    override fun showProgress() {
        supplierProgressLayout.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        supplierProgressLayout.visibility = View.GONE
    }

    override fun showPlaceholder() {
        supplierImgPlaceHolder.visibility = View.VISIBLE
    }

    override fun hidePlaceholder() {
        supplierImgPlaceHolder.visibility = View.GONE
    }

    override fun showSupplierList(supplierList: MutableList<Supplier>) {
        if (supplierRv != null){
            for (i in 0 until supplierList.size){
                dataSupplierList.add(supplierList[i])
                val index = dataSupplierList.lastIndex
                adapterRvSupplierList.notifyItemInserted(index)

                if (index == 0){
                    supplierSwipeRefreshLayout.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun showSupplierDetails(supplier: Supplier) {
    }

    override fun openAddSupplierPage() {
    }

    override fun openUpdateSupplierPage(supplier: Supplier) {
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                SupplierFragment().apply {
                    arguments = Bundle().apply {
                        //putString(ARG_PARAM1, param1)
                        //putString(ARG_PARAM2, param2)
                    }
                }
    }
}

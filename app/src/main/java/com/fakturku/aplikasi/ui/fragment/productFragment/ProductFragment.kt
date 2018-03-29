package com.fakturku.aplikasi.ui.fragment.productFragment

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
import com.fakturku.aplikasi.model.Product
import com.fakturku.aplikasi.ui.activity.DashboardActivity
import com.fakturku.aplikasi.ui.adapter.ProductListAdapter
import kotlinx.android.synthetic.main.fragment_product.*
import java.util.ArrayList

class ProductFragment : Fragment(), ProductContract.View {

    private lateinit var presenter: ProductPresenter
    private var isFragmentWasVisited: Boolean = false

    private var dataProductList: MutableList<Product> = ArrayList()
    private lateinit var adapterRvProductList: RecyclerView.Adapter<*>
    private lateinit var lmRvProductList: LinearLayoutManager
    private lateinit var animator: DefaultItemAnimator
    private lateinit var dividerItemDecoration: DividerItemDecoration

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if(isFragmentWasVisited){
            (activity as DashboardActivity).setTitleBar(R.string.product_title)
        }else{
            isFragmentWasVisited = true
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init presenter
        presenter = ProductPresenter(this@ProductFragment)

        //Init RecyclerView
        initRecyclerView()

        //Load Init Data
        presenter.loadProductListData(1)

        //UI handling & listener
        productFabAddProduct.setOnClickListener { presenter.addProduct()}
    }

    override fun initRecyclerView() {
        adapterRvProductList = ProductListAdapter(dataProductList, object : ProductListAdapter.OnItemClickListener{
            override fun onItemClick(product: Product) {
                presenter.seeProductDetails(product)
            }
        })
        lmRvProductList = LinearLayoutManager(activity)
        animator = DefaultItemAnimator()
        animator.addDuration = 300
        animator.removeDuration = 600

        dividerItemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        productRv.adapter = adapterRvProductList
        productRv.layoutManager = lmRvProductList
        productRv.itemAnimator = animator
        productRv.addItemDecoration(dividerItemDecoration)
        productRv.setHasFixedSize(true)
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
        productProgressLayout.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        productProgressLayout.visibility = View.GONE
    }

    override fun showPlaceholder() {
        productImgPlaceHolder.visibility = View.VISIBLE
    }

    override fun hidePlaceholder() {
        productImgPlaceHolder.visibility = View.GONE
    }

    override fun showProductList(productList: MutableList<Product>) {
        if (productRv != null){
            for (i in 0 until productList.size){
                dataProductList.add(productList[i])
                val index = dataProductList.lastIndex
                adapterRvProductList.notifyItemInserted(index)

                if (index == 0){
                    productSwipeRefreshLayout.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun showProductDetails(product: Product) {
    }

    override fun openAddProductPage() {
    }

    override fun openUpdateProductPage(product: Product) {
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ProductFragment().apply {
                    arguments = Bundle().apply {
                        //putString(ARG_PARAM1, param1)
                        //putString(ARG_PARAM2, param2)
                    }
                }
    }
}

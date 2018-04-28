package com.fakturku.aplikasi.ui.fragment.productFragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.model.Product
import com.fakturku.aplikasi.ui.activity.DashboardActivity
import com.fakturku.aplikasi.ui.activity.productDetails.ProductDetailsActivity
import com.fakturku.aplikasi.ui.activity.productForm.ProductFormActivity
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

    private var isLoadingData: Boolean = false
    private var page: Int = 1
    private var maxPage: Int = 0

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
        presenter.loadProductListData(page)

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

        val colorPrimaryDark = ContextCompat.getColor(activity as DashboardActivity, R.color.colorPrimaryDark)
        productSwipeRefreshLayout.setColorSchemeColors(colorPrimaryDark)
        productSwipeRefreshLayout.setOnRefreshListener {
            if (!isLoadingData){
                if((activity as DashboardActivity).isNetworkAvailable()){
                    page++
                    if (page <= maxPage) {
                        presenter.loadProductListData(page)
                    } else {
                        productSwipeRefreshLayout.isRefreshing = false
                        showSnackBar("Semua data sudah ditampilkan", 300)
                        productSwipeRefreshLayout.isEnabled = false
                    }
                }else{
                    productSwipeRefreshLayout.isRefreshing = false
                    showSnackBar("Periksa Koneksi Internet Anda ...",300)
                }
            }
        }
    }

    override fun showProgress() {
        isLoadingData = true
        if (page < 2) {
            productProgressLayout.visibility = View.VISIBLE
        }
    }

    override fun hideProgress() {
        isLoadingData = false
        if (page > 1) {
            productSwipeRefreshLayout.isRefreshing = false
        } else {
            productProgressLayout.visibility = View.GONE
        }
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

    override fun setTotalPage(totalPage: Int) {
        maxPage = totalPage
    }

    override fun showProductDetails(product: Product) {
        val intentSupplierDetails = Intent(activity, ProductDetailsActivity::class.java)
        intentSupplierDetails.putExtra(ProductDetailsActivity.INTENT_DATA_PRODUCT, product)
        startActivityForResult(intentSupplierDetails, INTENT_PRODUCT_DETAILS_CODE)
    }

    override fun openAddProductPage() {
        val intentAddProduct = Intent(activity, ProductFormActivity::class.java)
        startActivityForResult(intentAddProduct, INTENT_ADD_PRODUCT_CODE)
    }

    override fun openUpdateProductPage(product: Product) {
        val intentUpdateProduct = Intent(activity, ProductFormActivity::class.java)
        intentUpdateProduct.putExtra(ProductFormActivity.INTENT_PRODUCT_DATA, product)
        startActivityForResult(intentUpdateProduct, INTENT_UPDATE_PRODUCT_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            INTENT_PRODUCT_DETAILS_CODE->{
                when(resultCode){
                    INTENT_PRODUCT_DETAILS_UPDATE->{
                        if (data != null) {
                            val product: Product = data.getParcelableExtra(INTENT_PRODUCT_DETAILS_UPDATE_DATA)
                            presenter.updateProduct(product)
                        }
                    }
                    INTENT_PRODUCT_DETAILS_DELETE->{
                        Toast.makeText(activity,"DATA DELETED",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            INTENT_ADD_PRODUCT_CODE->{
                when(resultCode){
                    INTENT_ADD_PRODUCT_SUCCESS->{
                        Toast.makeText(activity,"DATA ADDED",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            INTENT_UPDATE_PRODUCT_CODE->{
                when(resultCode){
                    INTENT_UPDATE_PRODUCT_SUCCESS->{
                        Toast.makeText(activity,"DATA UPDATED",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else->{ super.onActivityResult(requestCode, resultCode, data) }
        }

    }

    private fun showSnackBar(msg: String, delayTime: Long){
        Handler().postDelayed({
            Snackbar.make(productCoordinatorLayout, msg, Snackbar.LENGTH_SHORT).show()
        }, delayTime)
    }

    companion object {
        const val INTENT_PRODUCT_DETAILS_CODE = 20
        const val INTENT_PRODUCT_DETAILS_UPDATE = 21
        const val INTENT_PRODUCT_DETAILS_DELETE = 22
        const val INTENT_PRODUCT_DETAILS_UPDATE_DATA: String = "IntentProductUpdateData"

        const val INTENT_ADD_PRODUCT_CODE = 30
        const val INTENT_ADD_PRODUCT_SUCCESS = 31

        const val INTENT_UPDATE_PRODUCT_CODE = 40
        const val INTENT_UPDATE_PRODUCT_SUCCESS = 41

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

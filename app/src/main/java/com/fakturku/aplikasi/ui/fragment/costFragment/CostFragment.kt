package com.fakturku.aplikasi.ui.fragment.costFragment

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
import com.fakturku.aplikasi.model.Cost
import com.fakturku.aplikasi.ui.activity.DashboardActivity
import com.fakturku.aplikasi.ui.activity.costDetails.CostDetailsActivity
import com.fakturku.aplikasi.ui.activity.costForm.CostFormActivity
import com.fakturku.aplikasi.ui.adapter.CostListAdapter
import kotlinx.android.synthetic.main.fragment_cost.*

class CostFragment : Fragment(), CostContract.View {

    private lateinit var presenter: CostPresenter
    private var isFragmentWasVisited: Boolean = false

    private var userId: Long = 0

    private var dataCostList: MutableList<Cost> = ArrayList()
    private lateinit var adapterRvCostList: RecyclerView.Adapter<*>
    private lateinit var lmRvCostList: LinearLayoutManager
    private lateinit var animator: DefaultItemAnimator
    private lateinit var dividerItemDecoration: DividerItemDecoration

    private var isLoadingData: Boolean = false
    private var page: Int = 1
    private var lastPage: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if(isFragmentWasVisited){
            (activity as DashboardActivity).setTitleBar(R.string.cost_title)
        }else{
            isFragmentWasVisited = true
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cost, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Get User Id
        userId = (activity as DashboardActivity).getUserId()

        //Init Presenter
        presenter = CostPresenter(this@CostFragment)

        //Init RecyclerView
        initRecyclerView()

        //Load Init Data
        presenter.loadCostListData(userId, page)

        //UI handling & listener
        costFabAddCost.setOnClickListener { presenter.addCost(userId)}
    }

    override fun initRecyclerView() {
        adapterRvCostList = CostListAdapter(dataCostList, object : CostListAdapter.OnItemClickListener{
            override fun onItemClick(cost: Cost) {
                presenter.seeCostDetails(cost)
            }
        })
        lmRvCostList = LinearLayoutManager(activity)
        animator = DefaultItemAnimator()
        animator.addDuration = 300
        animator.removeDuration = 600

        dividerItemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        costRv.adapter = adapterRvCostList
        costRv.layoutManager = lmRvCostList
        costRv.itemAnimator = animator
        costRv.addItemDecoration(dividerItemDecoration)
        costRv.setHasFixedSize(true)

        val colorPrimaryDark = ContextCompat.getColor(activity as DashboardActivity, R.color.colorPrimaryDark)
        costSwipeRefreshLayout.setColorSchemeColors(colorPrimaryDark)
        costSwipeRefreshLayout.setOnRefreshListener {
            if (!isLoadingData){
                if((activity as DashboardActivity).isNetworkAvailable()){
                    page++
                    if (page <= lastPage) {
                        presenter.loadCostListData(userId, page)
                    } else {
                        costSwipeRefreshLayout.isRefreshing = false
                        showSnackBar("Semua data sudah ditampilkan", 300)
                        costSwipeRefreshLayout.isEnabled = false
                    }
                }else{
                    costSwipeRefreshLayout.isRefreshing = false
                    showSnackBar("Periksa Koneksi Internet Anda ...",300)
                }
            }
        }
    }

    override fun showProgress() {
        isLoadingData = true
        if (page < 2) {
            costProgressBar.visibility = View.VISIBLE
        }
    }

    override fun hideProgress() {
        isLoadingData = false
        if (page > 1) {
            costSwipeRefreshLayout.isRefreshing = false
        } else {
            costProgressBar.visibility = View.GONE
        }
    }

    override fun showPlaceholder() {
        costImgPlaceHolder.visibility = View.VISIBLE
    }

    override fun hidePlaceholder() {
        costImgPlaceHolder.visibility = View.GONE
    }

    override fun showCostList(costList: MutableList<Cost>) {
        if (costRv != null){
            for (i in 0 until costList.size){
                dataCostList.add(costList[i])
                val index = dataCostList.lastIndex
                adapterRvCostList.notifyItemInserted(index)

                if (index == 0){
                    costSwipeRefreshLayout.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun setLastPage(lastPage: Int) {
        this.lastPage = lastPage
    }

    override fun showCostDetails(cost: Cost) {
        val intentCostDetails = Intent(activity, CostDetailsActivity::class.java)
        intentCostDetails.putExtra(CostDetailsActivity.INTENT_DATA_COST, cost)
        startActivityForResult(intentCostDetails, INTENT_COST_DETAILS_CODE)
    }

    override fun openAddCostPage(userId: Long) {
        val intentCostProduct = Intent(activity, CostFormActivity::class.java)
        intentCostProduct.putExtra(CostFormActivity.INTENT_USER_ID, userId)
        startActivityForResult(intentCostProduct, INTENT_ADD_COST_CODE)
    }

    override fun openUpdateCostPage(userId: Long, cost: Cost) {
        val intentCostProduct = Intent(activity, CostFormActivity::class.java)
        intentCostProduct.putExtra(CostFormActivity.INTENT_USER_ID, userId)
        intentCostProduct.putExtra(CostFormActivity.INTENT_COST_DATA, cost)
        startActivityForResult(intentCostProduct, INTENT_UPDATE_COST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            INTENT_COST_DETAILS_CODE->{
                when(resultCode){
                    INTENT_COST_DETAILS_UPDATE->{
                        if (data != null) {
                            val cost: Cost = data.getParcelableExtra(INTENT_COST_DETAILS_UPDATE_DATA)
                            presenter.updateCost(userId, cost)
                        }
                    }
                    INTENT_COST_DETAILS_DELETE->{
                        Toast.makeText(activity,"DATA DELETED",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            INTENT_ADD_COST_CODE->{
                when(resultCode){
                    INTENT_ADD_COST_SUCCESS->{
                        Toast.makeText(activity,"DATA ADDED", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            INTENT_UPDATE_COST_CODE->{
                when(resultCode){
                    INTENT_UPDATE_COST_SUCCESS->{
                        Toast.makeText(activity,"DATA UPDATED",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else->{ super.onActivityResult(requestCode, resultCode, data) }
        }

    }

    private fun showSnackBar(msg: String, delayTime: Long){
        Handler().postDelayed({
            Snackbar.make(costCoordinatorLayout, msg, Snackbar.LENGTH_SHORT).show()
        }, delayTime)
    }

    companion object {
        const val INTENT_COST_DETAILS_CODE = 20
        const val INTENT_COST_DETAILS_UPDATE = 21
        const val INTENT_COST_DETAILS_DELETE = 22
        const val INTENT_COST_DETAILS_UPDATE_DATA: String = "IntentCostUpdateData"

        const val INTENT_ADD_COST_CODE = 30
        const val INTENT_ADD_COST_SUCCESS = 31

        const val INTENT_UPDATE_COST_CODE = 40
        const val INTENT_UPDATE_COST_SUCCESS = 41

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                CostFragment().apply {
                    arguments = Bundle().apply {
                        //putString(ARG_PARAM1, param1)
                        //putString(ARG_PARAM2, param2)
                    }
                }
    }
}

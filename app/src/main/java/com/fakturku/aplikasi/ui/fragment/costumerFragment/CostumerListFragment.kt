package com.fakturku.aplikasi.ui.fragment.costumerFragment

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
import com.fakturku.aplikasi.model.Costumer
import com.fakturku.aplikasi.ui.activity.DashboardActivity
import com.fakturku.aplikasi.ui.activity.costumerForm.CostumerFormActivity
import com.fakturku.aplikasi.ui.activity.costumerDetails.CostumerDetailsActivity
import com.fakturku.aplikasi.ui.adapter.CostumerListAdapter
import kotlinx.android.synthetic.main.fragment_costumer_list.*
import java.util.ArrayList

class CostumerListFragment : Fragment(), CostumerListContract.View {

    private lateinit var presenter: CostumerListPresenter

    private var isFragmentWasVisited: Boolean = false

    private var userId: Long = 0

    private var dataCostumerList: MutableList<Costumer> = ArrayList()
    private lateinit var adapterRvCostumerList: RecyclerView.Adapter<*>
    private lateinit var lmRvCostumerList: LinearLayoutManager
    private lateinit var animator: DefaultItemAnimator
    private lateinit var dividerItemDecoration: DividerItemDecoration

    private var isLoadingData: Boolean = false
    private var page: Int = 1
    private var lastPage: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if(isFragmentWasVisited){
            (activity as DashboardActivity).setTitleBar(R.string.costumer_title)
        }else{
            isFragmentWasVisited = true
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_costumer_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Get User Id
        userId = (activity as DashboardActivity).getUserId()

        //Init Presenter
        presenter = CostumerListPresenter(this@CostumerListFragment)

        //Init Recycler View
        initRecyclerView()

        //Load Init Costumer List Data
        presenter.loadCostumerListData(userId, page)

        //UI handling & listener
        costumerListFabAddCostumer.setOnClickListener{presenter.addCostumer(userId)}
    }

    override fun initRecyclerView() {
        adapterRvCostumerList = CostumerListAdapter(dataCostumerList, object : CostumerListAdapter.OnItemClickListener{
            override fun onItemClick(costumer: Costumer) {
                presenter.seeCostumerDetails(userId, costumer)
            }
        })

        lmRvCostumerList = LinearLayoutManager(activity)
        animator = DefaultItemAnimator()
        animator.addDuration = 300
        animator.removeDuration = 600

        dividerItemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        costumerListRv.adapter = adapterRvCostumerList
        costumerListRv.layoutManager = lmRvCostumerList
        costumerListRv.itemAnimator = animator
        costumerListRv.addItemDecoration(dividerItemDecoration)
        costumerListRv.setHasFixedSize(true)

        val colorPrimaryDark = ContextCompat.getColor(activity as DashboardActivity, R.color.colorPrimaryDark)
        costumerListSwipeRefreshLayout.setColorSchemeColors(colorPrimaryDark)
        costumerListSwipeRefreshLayout.setOnRefreshListener {
            if (!isLoadingData){
                if((activity as DashboardActivity).isNetworkAvailable()){
                    page++
                    if (page <= lastPage) {
                        presenter.loadCostumerListData(userId, page)
                    } else {
                        costumerListSwipeRefreshLayout.isRefreshing = false
                        showSnackBar("Semua data sudah ditampilkan", 300)
                        costumerListSwipeRefreshLayout.isEnabled = false
                    }
                }else{
                    costumerListSwipeRefreshLayout.isRefreshing = false
                    showSnackBar("Periksa Koneksi Internet Anda ...",300)
                }
            }
        }
    }

    override fun showProgress() {
        isLoadingData = true
        if (page < 2) {
            costumerListProgressBar.visibility = View.VISIBLE
        }
    }

    override fun hideProgress() {
        isLoadingData = false
        if (page > 1) {
            costumerListSwipeRefreshLayout.isRefreshing = false
        } else {
            costumerListProgressBar.visibility = View.GONE
        }
    }

    override fun showPlaceholder() {
        costumerListImgPlaceHolder.visibility = View.VISIBLE
    }

    override fun hidePlaceholder() {
        costumerListImgPlaceHolder.visibility = View.GONE
    }

    override fun showCostumerList(costumerList: MutableList<Costumer>) {
        if (costumerListRv != null){
            for (i in 0 until costumerList.size){
                dataCostumerList.add(costumerList[i])
                val index = dataCostumerList.lastIndex
                adapterRvCostumerList.notifyItemInserted(index)

                if (index == 0){
                    costumerListSwipeRefreshLayout.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun setLastPage(lastPage: Int) {
        this.lastPage = lastPage
    }

    override fun showCustomerDetails(userId: Long, costumer: Costumer) {
        val intentCostumerDetails = Intent(activity, CostumerDetailsActivity::class.java)
        intentCostumerDetails.putExtra(CostumerDetailsActivity.INTENT_USER_ID, userId)
        intentCostumerDetails.putExtra(CostumerDetailsActivity.INTENT_DATA_COSTUMER, costumer)
        startActivityForResult(intentCostumerDetails, INTENT_COSTUMER_DETAILS_CODE)
    }

    override fun openAddCostumerPage(userId: Long) {
        val intentAddCostumer = Intent(activity, CostumerFormActivity::class.java)
        intentAddCostumer.putExtra(CostumerFormActivity.INTENT_USER_ID, userId)
        startActivityForResult(intentAddCostumer, INTENT_ADD_COSTUMER_CODE)
    }

    override fun openUpdateCostumerPage(userId: Long, costumer: Costumer) {
        val intentUpdateCostumer = Intent(activity, CostumerFormActivity::class.java)
        intentUpdateCostumer.putExtra(CostumerFormActivity.INTENT_USER_ID, userId)
        intentUpdateCostumer.putExtra(CostumerFormActivity.INTENT_COSTUMER_DATA, costumer)
        startActivityForResult(intentUpdateCostumer, INTENT_UPDATE_COSTUMER_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            INTENT_COSTUMER_DETAILS_CODE->{
                when(resultCode){
                    INTENT_COSTUMER_DETAILS_UPDATE->{
                        if (data != null) {
                            val costumer: Costumer = data.getParcelableExtra(INTENT_COSTUMER_DETAILS_UPDATE_DATA)
                            presenter.updateCostumer(userId, costumer)
                        }
                    }
                    INTENT_COSTUMER_DETAILS_DELETE->{
                        Toast.makeText(activity,"DATA DELETED",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            INTENT_ADD_COSTUMER_CODE->{
                when(resultCode){
                    INTENT_ADD_COSTUMER_SUCCESS->{
                        Toast.makeText(activity,"DATA ADDED",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            INTENT_UPDATE_COSTUMER_CODE->{
                when(resultCode){
                    INTENT_UPDATE_COSTUMER_SUCCESS->{
                        Toast.makeText(activity,"DATA UPDATED",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else->{ super.onActivityResult(requestCode, resultCode, data) }
        }

    }

    private fun showSnackBar(msg: String, delayTime: Long){
        Handler().postDelayed({
            Snackbar.make(costumerListCoordinatorLayout, msg, Snackbar.LENGTH_SHORT).show()
        }, delayTime)
    }

    companion object {
        const val INTENT_COSTUMER_DETAILS_CODE = 20
        const val INTENT_COSTUMER_DETAILS_UPDATE = 21
        const val INTENT_COSTUMER_DETAILS_DELETE = 22
        const val INTENT_COSTUMER_DETAILS_UPDATE_DATA: String = "IntentCostumerUpdateData"

        const val INTENT_ADD_COSTUMER_CODE = 30
        const val INTENT_ADD_COSTUMER_SUCCESS = 31

        const val INTENT_UPDATE_COSTUMER_CODE = 40
        const val INTENT_UPDATE_COSTUMER_SUCCESS = 41

        fun newInstance(param1: String, param2: String): CostumerListFragment {
            val fragment = CostumerListFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
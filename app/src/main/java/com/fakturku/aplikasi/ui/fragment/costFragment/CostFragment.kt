package com.fakturku.aplikasi.ui.fragment.costFragment

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
import com.fakturku.aplikasi.model.Cost
import com.fakturku.aplikasi.ui.activity.DashboardActivity
import com.fakturku.aplikasi.ui.adapter.CostListAdapter
import kotlinx.android.synthetic.main.fragment_cost.*

class CostFragment : Fragment(), CostContract.View {

    private lateinit var presenter: CostPresenter
    private var isFragmentWasVisited: Boolean = false

    private var dataCostList: MutableList<Cost> = ArrayList()
    private lateinit var adapterRvCostList: RecyclerView.Adapter<*>
    private lateinit var lmRvCostList: LinearLayoutManager
    private lateinit var animator: DefaultItemAnimator
    private lateinit var dividerItemDecoration: DividerItemDecoration

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

        //Init Presenter
        presenter = CostPresenter(this@CostFragment)

        //Init RecyclerView
        initRecyclerView()

        //Load Init Data
        presenter.loadCostListData(1)

        //UI handling & listener
        costFabAddCost.setOnClickListener { presenter.addCost()}
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
        costProgressLayout.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        costProgressLayout.visibility = View.GONE
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

    override fun showCostDetails(cost: Cost) {
    }

    override fun openAddCostPage() {
    }

    override fun openUpdateCostPage(cost: Cost) {
    }

    companion object {

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

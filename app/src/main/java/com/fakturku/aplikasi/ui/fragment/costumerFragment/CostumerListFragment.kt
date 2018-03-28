package com.fakturku.aplikasi.ui.fragment.costumerFragment

import android.content.Intent
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

    private var dataCostumerList: MutableList<Costumer> = ArrayList()
    private lateinit var adapterRvCostumerList: RecyclerView.Adapter<*>
    private lateinit var lmRvCostumerList: LinearLayoutManager
    private lateinit var animator: DefaultItemAnimator
    private lateinit var dividerItemDecoration: DividerItemDecoration

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

/*

        val gson = GsonBuilder().create()

        val retrofit: Retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://localhost:8000/")
                .build()

        val networkService: NetworkService = retrofit.create(
                NetworkService::class.java
        )

        networkService.getCostumerList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {costumerList: CostumerList? ->
                            if (costumerList != null){
                                setData(costumerList)
                            }
                        },
                        { error: Throwable? ->
                            Log.e("Error", error!!.message)
                        }
                )

*/
        //Init Presenter
        presenter = CostumerListPresenter(this@CostumerListFragment)

        //Init Recycler View
        initRecyclerView()

        //Load Init Costumer List Data
        presenter.loadCostumerListData(1)

        //UI handling & listener
        costumerListFabAddCostumer.setOnClickListener{presenter.addCostumer()}
    }

    override fun initRecyclerView() {
        adapterRvCostumerList = CostumerListAdapter(dataCostumerList, object : CostumerListAdapter.OnItemClickListener{
            override fun onItemClick(costumer: Costumer) {
                presenter.seeCostumerDetails(costumer)
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
        costumerListProgressLayout.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        costumerListProgressLayout.visibility = View.GONE
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

    override fun showCustomerDetails(costumer: Costumer) {
        val intentCostumerDetails = Intent(activity, CostumerDetailsActivity::class.java)
        intentCostumerDetails.putExtra(CostumerDetailsActivity.INTENT_DATA_COSTUMER, costumer)
        startActivity(intentCostumerDetails)
    }

    override fun openAddCostumerPage() {
        val intentAddCostumer = Intent(activity, CostumerFormActivity::class.java)
        startActivityForResult(intentAddCostumer, INTENT_ADD_COSTUMER_CODE)
    }

/*

    private fun setData(costumerList: CostumerList) {
        Log.d("TES", costumerList.toString())
    }

*/
    companion object {
        const val INTENT_ADD_COSTUMER_CODE = 30

        fun newInstance(param1: String, param2: String): CostumerListFragment {
            val fragment = CostumerListFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
package com.fakturku.aplikasi.ui.fragment.costumerFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.model.Costumer
import com.fakturku.aplikasi.model.CostumerList
import com.fakturku.aplikasi.networking.NetworkService
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CostumerListFragment : Fragment(), CostumerListContract.View {

    private lateinit var presenter: CostumerListPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
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



    }

    //View Function
    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun showPlaceholder() {
    }

    override fun hidePlaceholder() {
    }

    override fun showCostumerList(costumerList: MutableList<Costumer>) {
    }

    override fun showCustomerDetails(costumer: Costumer) {
    }

    override fun openAddCostumerPage() {
    }



/*

    private fun setData(costumerList: CostumerList) {
        Log.d("TES", costumerList.toString())
    }

*/
    companion object {
        fun newInstance(param1: String, param2: String): CostumerListFragment {
            val fragment = CostumerListFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
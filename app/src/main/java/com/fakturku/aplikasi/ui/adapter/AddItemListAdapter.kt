package com.fakturku.aplikasi.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.model.Product
import com.fakturku.aplikasi.utils.MyCurrencyFormat
import kotlinx.android.synthetic.main.add_item_list_adapter.view.*

class AddItemListAdapter(private val dataItemList: List<Product>)
    : RecyclerView.Adapter<AddItemListAdapter.ViewHolder>(){

    private lateinit var mContext: Context

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        internal var txtName: TextView = itemView.addItemListAdapterTxtName
        internal var etTotalItem: EditText = itemView.addItemListAdapterEtTotalItem
        internal var etPrice: EditText = itemView.addItemListAdapterEtPrice
        internal var txtTotal: TextView = itemView.addItemListAdapterTxtTotal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.add_item_list_adapter, parent,
                false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtName.text = dataItemList[position].name!!
        holder.etPrice.setText(dataItemList[position].sell_price!!.toString())
        val totalItem = 0
        val price = holder.etPrice.text.toString().toInt()
        val total = totalItem * price
        val strTotal = MyCurrencyFormat.rupiah(total)
        holder.txtTotal.text = strTotal
    }

    override fun getItemCount(): Int {
        return dataItemList.size
    }

}
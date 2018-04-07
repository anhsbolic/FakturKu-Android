package com.fakturku.aplikasi.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.model.Product
import com.fakturku.aplikasi.utils.MyCurrencyFormat
import kotlinx.android.synthetic.main.invoice_item_list_adapter.view.*

class InvoiceItemListAdapter(private val dataItemList: List<Product>)
    : RecyclerView.Adapter<InvoiceItemListAdapter.ViewHolder>(){

    private lateinit var mContext: Context


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        internal var txtName: TextView = itemView.invoiceItemListAdapterName
        internal var txtTotalItem: TextView = itemView.invoiceItemListAdapterTotalItem
        internal var txtTotalPrice: TextView = itemView.invoiceItemListAdapterTotalPrice
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.invoice_item_list_adapter, parent,
                false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtName.text = dataItemList[position].name
        val totalItem = 20
        val totalPrice = totalItem * dataItemList[position].sell_price!!
        holder.txtTotalItem.text = totalItem.toString()
        holder.txtTotalPrice.text = MyCurrencyFormat.rupiah(totalPrice)
    }

    override fun getItemCount(): Int {
        return dataItemList.size
    }

}
package com.fakturku.aplikasi.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.model.Product
import com.fakturku.aplikasi.utils.MyCurrencyFormat
import kotlinx.android.synthetic.main.add_item_list_adapter.view.*

class AddItemListAdapter(private val dataItemList: List<Product>,
                         private val onItemUpdate: AddItemListAdapter.OnItemUpdateListener)
    : RecyclerView.Adapter<AddItemListAdapter.ViewHolder>(){

    private lateinit var mContext: Context

    interface OnItemUpdateListener{
        fun deleteItem(product: Product, adapterPosition: Int)
        fun onTotalUpdate(intTotal: Int, position: Int)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        internal var txtName: TextView = itemView.addItemListAdapterTxtName
        internal var etTotalItem: EditText = itemView.addItemListAdapterEtTotalItem
        internal var etPrice: EditText = itemView.addItemListAdapterEtPrice
        internal var txtTotal: TextView = itemView.addItemListAdapterTxtTotal
        internal var btnDelete: ImageButton = itemView.addItemListAdapterBtnDelete
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
        onItemUpdate.onTotalUpdate(total, position)
        val strTotal = MyCurrencyFormat.rupiah(total)
        holder.txtTotal.text = strTotal

        //Calculate Total
        holder.etTotalItem.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                if (editable != null) {
                    val strTotalItem = editable.toString()
                    val intTotalItem = if (strTotalItem.isNotEmpty()) {
                        strTotalItem.toInt()
                    } else{
                        0
                    }

                    val strPrice = holder.etPrice.text.toString()
                    val intPrice = if (strPrice.isNotEmpty()){
                        strPrice.toInt()
                    } else {
                        0
                    }

                    val intTotal = intTotalItem * intPrice
                    onItemUpdate.onTotalUpdate(intTotal, position)
                    val txtTotal = MyCurrencyFormat.rupiah(intTotal)
                    holder.txtTotal.text = txtTotal
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        holder.etPrice.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                if (editable != null) {

                    val strPrice = editable.toString()
                    val intPrice = if (strPrice.isNotEmpty()){
                        strPrice.toInt()
                    } else {
                        0
                    }

                    val strTotalItem = holder.etTotalItem.text.toString()
                    val intTotalItem = if (strTotalItem.isNotEmpty()) {
                        strTotalItem.toInt()
                    } else{
                        0
                    }

                    val intTotal = intTotalItem * intPrice
                    onItemUpdate.onTotalUpdate(intTotal, position)
                    val txtTotal = MyCurrencyFormat.rupiah(intTotal)
                    holder.txtTotal.text = txtTotal
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        //Delete Item
        holder.btnDelete.setOnClickListener {
            onItemUpdate.deleteItem(dataItemList[holder.adapterPosition], holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return dataItemList.size
    }

}
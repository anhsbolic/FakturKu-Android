package com.fakturku.aplikasi.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.model.Supplier
import kotlinx.android.synthetic.main.supplier_list_adapter.view.*

class SupplierListAdapter(private val dataSupplierList: List<Supplier>,
                          private val onItemClickListener: SupplierListAdapter.OnItemClickListener)
    : RecyclerView.Adapter<SupplierListAdapter.ViewHolder>(){

    private lateinit var mContext: Context

    interface OnItemClickListener{
        fun onItemClick(supplier: Supplier)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        internal var imgCostumer: ImageView = itemView.supplierListAdapterImg
        internal var txtName: TextView = itemView.supplierListAdapterName
        internal var txtPhone: TextView = itemView.supplierListAdapterPhone
        internal var txtAddress: TextView = itemView.supplierListAdapterAddress

        fun setOnClickListener(onClickListener: View.OnClickListener){
            itemView.setOnClickListener(onClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.supplier_list_adapter, parent,
                false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val supplierName = dataSupplierList[position].name!!
        holder.txtName.text = supplierName
        holder.txtPhone.text = dataSupplierList[position].phone!!
        holder.txtAddress.text = dataSupplierList[position].address

        val initialName = supplierName[0].toString()
        val colorGenerator: ColorGenerator = ColorGenerator.MATERIAL
        val color: Int = colorGenerator.getColor(initialName)
        val textDrawable: TextDrawable = TextDrawable.builder().beginConfig()
                .width(100)
                .height(100)
                .endConfig()
                .buildRound(initialName, color)
        holder.imgCostumer.setImageDrawable(textDrawable)

        holder.setOnClickListener(View.OnClickListener {
            onItemClickListener.onItemClick(dataSupplierList[holder.adapterPosition])
        })
    }

    override fun getItemCount(): Int {
        return dataSupplierList.size
    }

}
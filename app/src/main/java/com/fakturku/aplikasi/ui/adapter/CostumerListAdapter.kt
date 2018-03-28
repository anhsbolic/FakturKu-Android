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
import com.fakturku.aplikasi.model.Costumer
import kotlinx.android.synthetic.main.costumer_list_adapter.view.*

class CostumerListAdapter(private val dataCostumerList: List<Costumer>,
                          private val onItemClickListener: CostumerListAdapter.OnItemClickListener)
    : RecyclerView.Adapter<CostumerListAdapter.ViewHolder>(){

    private lateinit var mContext: Context

    interface OnItemClickListener{
        fun onItemClick(costumer: Costumer)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        internal var imgCostumer: ImageView = itemView.costumerListAdapterImg
        internal var txtName: TextView = itemView.costumerListAdapterName
        internal var txtPhone: TextView = itemView.costumerListAdapterPhone
        internal var txtAddress: TextView = itemView.costumerListAdapterAddress

        fun setOnClickListener(onClickListener: View.OnClickListener){
            itemView.setOnClickListener(onClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.costumer_list_adapter, parent,
                false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val costumerName = dataCostumerList[position].name!!
        holder.txtName.text = costumerName
        holder.txtPhone.text = dataCostumerList[position].phone!!
        holder.txtAddress.text = dataCostumerList[position].address!!

        val initialName = costumerName[0].toString()
        val colorGenerator: ColorGenerator = ColorGenerator.MATERIAL
        val color: Int = colorGenerator.getColor(initialName)
        val textDrawable: TextDrawable = TextDrawable.builder().beginConfig()
                .width(100)
                .height(100)
                .endConfig()
                .buildRound(initialName, color)
        holder.imgCostumer.setImageDrawable(textDrawable)

        holder.setOnClickListener(View.OnClickListener {
            onItemClickListener.onItemClick(dataCostumerList[holder.adapterPosition])
        })
    }

    override fun getItemCount(): Int {
        return dataCostumerList.size
    }

}
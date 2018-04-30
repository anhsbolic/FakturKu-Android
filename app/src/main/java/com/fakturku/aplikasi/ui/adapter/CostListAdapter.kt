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
import com.fakturku.aplikasi.model.Cost
import com.fakturku.aplikasi.utils.MyCurrencyFormat
import kotlinx.android.synthetic.main.cost_list_adapter.view.*

class CostListAdapter(private val dataCostList: List<Cost>,
                      private val onItemClickListener: CostListAdapter.OnItemClickListener)
    : RecyclerView.Adapter<CostListAdapter.ViewHolder>(){

    private lateinit var mContext: Context

    interface OnItemClickListener{
        fun onItemClick(cost: Cost)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        internal var imgCost: ImageView = itemView.costListAdapterImg
        internal var txtName: TextView = itemView.costListAdapterName
        internal var txtCostPrice: TextView = itemView.costListAdapterCostPrice

        fun setOnClickListener(onClickListener: View.OnClickListener){
            itemView.setOnClickListener(onClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.cost_list_adapter, parent,
                false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val costName = dataCostList[position].name!!
        holder.txtName.text = costName

        val costPrice = dataCostList[position].unit_cost
        if (costPrice != null){
            holder.txtCostPrice.text = MyCurrencyFormat.rupiah(costPrice)
        }
        val initialName = costName[0].toString()
        val colorGenerator: ColorGenerator = ColorGenerator.MATERIAL
        val color: Int = colorGenerator.getColor(initialName)
        val textDrawable: TextDrawable = TextDrawable.builder().beginConfig()
                .width(100)
                .height(100)
                .endConfig()
                .buildRound(initialName, color)
        holder.imgCost.setImageDrawable(textDrawable)

        holder.setOnClickListener(View.OnClickListener {
            onItemClickListener.onItemClick(dataCostList[holder.adapterPosition])
        })
    }

    override fun getItemCount(): Int {
        return dataCostList.size
    }

}
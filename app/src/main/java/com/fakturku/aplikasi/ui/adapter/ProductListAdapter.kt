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
import com.fakturku.aplikasi.model.Product
import kotlinx.android.synthetic.main.product_list_adapter.view.*

class ProductListAdapter(private val dataProductList: List<Product>,
                         private val onItemClickListener: ProductListAdapter.OnItemClickListener)
    : RecyclerView.Adapter<ProductListAdapter.ViewHolder>(){

    private lateinit var mContext: Context

    interface OnItemClickListener{
        fun onItemClick(product: Product)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        internal var imgProduct: ImageView = itemView.productListAdapterImg
        internal var txtName: TextView = itemView.productListAdapterName
        internal var txtBuyPrice: TextView = itemView.productListAdapterBuyPrice
        internal var txtSellPrice: TextView = itemView.productListAdapterSellPrice

        fun setOnClickListener(onClickListener: View.OnClickListener){
            itemView.setOnClickListener(onClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.product_list_adapter, parent,
                false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productName = dataProductList[position].name!!
        holder.txtName.text = productName
        holder.txtBuyPrice.text = dataProductList[position].buy_price
        holder.txtSellPrice.text = dataProductList[position].sell_price

        val initialName = productName[0].toString()
        val colorGenerator: ColorGenerator = ColorGenerator.MATERIAL
        val color: Int = colorGenerator.getColor(initialName)
        val textDrawable: TextDrawable = TextDrawable.builder().beginConfig()
                .width(100)
                .height(100)
                .endConfig()
                .buildRound(initialName, color)
        holder.imgProduct.setImageDrawable(textDrawable)

        holder.setOnClickListener(View.OnClickListener {
            onItemClickListener.onItemClick(dataProductList[holder.adapterPosition])
        })
    }

    override fun getItemCount(): Int {
        return dataProductList.size
    }
}
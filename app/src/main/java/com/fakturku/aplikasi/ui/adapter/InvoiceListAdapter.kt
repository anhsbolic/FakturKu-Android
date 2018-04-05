package com.fakturku.aplikasi.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.model.Invoice
import com.fakturku.aplikasi.utils.InvoiceStatus
import com.fakturku.aplikasi.utils.MyCurrencyFormat
import com.fakturku.aplikasi.utils.MyDateFormatter
import kotlinx.android.synthetic.main.invoice_list_adapter.view.*

class InvoiceListAdapter(private val dataInvoiceList: List<Invoice>,
                         private val onItemClickListener: InvoiceListAdapter.OnItemClickListener)
    : RecyclerView.Adapter<InvoiceListAdapter.ViewHolder>(){

    private lateinit var mContext: Context

    interface OnItemClickListener{
        fun onItemClick(invoice: Invoice)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        internal var txtStatus: TextView = itemView.invoiceListAdapterTxtStatus
//        internal var txtId: TextView = itemView.invoiceListAdapterTxtId
        internal var txtCreatedDate: TextView = itemView.invoiceListAdapterTxtCreatedDate
        internal var txtCostumerName: TextView = itemView.invoiceListAdapterTxtCostumerName
        internal var txtTotal: TextView = itemView.invoiceListAdapterTxtTotal

        fun setOnClickListener(onClickListener: View.OnClickListener){
            itemView.setOnClickListener(onClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.invoice_list_adapter, parent,
                false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val idStatus = dataInvoiceList[position].id_status
        when (idStatus){
            0->{
                holder.txtStatus.text = InvoiceStatus.DEBT.status
                holder.txtStatus.setTextColor(ContextCompat.getColor(mContext, R.color.colorTextBlack))
                holder.txtStatus.setBackgroundResource(R.drawable.bg_invoice_debt)
                holder.txtTotal.setTextColor(ContextCompat.getColor(mContext, R.color.colorTextInvoiceDebt))
            }
            1->{
                holder.txtStatus.text = InvoiceStatus.PAID.status
                holder.txtStatus.setBackgroundResource(R.drawable.bg_round_invoice_paid)
                holder.txtTotal.setTextColor(ContextCompat.getColor(mContext, R.color.colorTextInvoicePaid))
            }
            2->{
                holder.txtStatus.text = InvoiceStatus.DRAFT.status
                holder.txtStatus.setBackgroundResource(R.drawable.bg_invoice_draft)
                holder.txtTotal.setTextColor(ContextCompat.getColor(mContext, R.color.colorTextInvoiceDraft))
            }
        }
//        holder.txtId.text = dataInvoiceList[position].id
        val date = MyDateFormatter.stringToDateMonthYearBahasa(dataInvoiceList[position].created_date!!)
        holder.txtCreatedDate.text = date

        val idTransactionType = dataInvoiceList[position].id_transaction_type
        when(idTransactionType){
            0->{ holder.txtCostumerName.text = dataInvoiceList[position].costumer }
            1->{ holder.txtCostumerName.text = dataInvoiceList[position].supplier }
            2->{ holder.txtCostumerName.text = dataInvoiceList[position].supplier }
        }
        holder.txtTotal.text = MyCurrencyFormat.rupiah(dataInvoiceList[position].total!!)

        holder.setOnClickListener(View.OnClickListener {
            onItemClickListener.onItemClick(dataInvoiceList[holder.adapterPosition])
        })
    }

    override fun getItemCount(): Int {
        return dataInvoiceList.size
    }
}
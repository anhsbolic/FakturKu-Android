package com.fakturku.aplikasi.ui.activity.invoiceDetails

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.widget.Toast
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.helper.BottomNavigationViewHelper
import com.fakturku.aplikasi.model.Invoice
import com.fakturku.aplikasi.model.Product
import com.fakturku.aplikasi.ui.adapter.InvoiceItemListAdapter
import com.fakturku.aplikasi.utils.InvoiceStatus
import com.fakturku.aplikasi.utils.MyCurrencyFormat
import com.fakturku.aplikasi.utils.MyDateFormatter
import kotlinx.android.synthetic.main.activity_invoice_details.*
import kotlinx.android.synthetic.main.invoice_details_bottom_sheet.view.*

class InvoiceDetailsActivity : AppCompatActivity(), InvoiceDetailsContract.View {

    private lateinit var presenter: InvoiceDetailsPresenter
    private lateinit var invoice: Invoice
    private var id: String? = null
    private var dueDate : String? = null
    private var idTransactionType : Int? = null
    private var costumer : String? = null
    private var supplier : String? = null
    private var tax : Double? = null
    private var total : Int? = null
    private var idStatus : Int? = null
    private var createdDate : String? = null
    private var updatedDate : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoice_details)

        //Init Bottom Nav View
        BottomNavigationViewHelper.disableShiftMode(invoiceDetailsBottomNav)
        invoiceDetailsBottomNav.setOnNavigationItemSelectedListener {item: MenuItem ->
            navActions(item.itemId)
            return@setOnNavigationItemSelectedListener true
        }

        //Init Presenter
        presenter = InvoiceDetailsPresenter(this@InvoiceDetailsActivity)

        //Get & set data
        if (intent.hasExtra(INTENT_DATA_INVOICE)) {
            invoice = intent.getParcelableExtra(INTENT_DATA_INVOICE)
            presenter.setDataToUI(invoice)
        }

    }

    @SuppressLint("InflateParams")
    private fun navActions(itemId: Int) {
        when(itemId){
            R.id.invoice_bottom_menu_send ->{
                Toast.makeText(this@InvoiceDetailsActivity, "KIRIM", Toast.LENGTH_SHORT).show()
            }

            R.id.invoice_bottom_menu_edit ->{
                Toast.makeText(this@InvoiceDetailsActivity, "UBAH", Toast.LENGTH_SHORT).show()
            }

            R.id.invoice_bottom_menu_details ->{
            }

            R.id.invoice_bottom_menu_pay ->{
                Toast.makeText(this@InvoiceDetailsActivity, "BAYAR", Toast.LENGTH_SHORT).show()
            }

            R.id.invoice_bottom_menu_more ->{
                val view = layoutInflater.inflate(R.layout.invoice_details_bottom_sheet,null)
                val bottomSheetDialog = BottomSheetDialog(this@InvoiceDetailsActivity)
                bottomSheetDialog.setContentView(view)
                bottomSheetDialog.show()
                view.invoiceDetailsBottomSheetDelete.setOnClickListener {
                    Toast.makeText(this@InvoiceDetailsActivity, "HAPUS", Toast.LENGTH_SHORT).show()
                    bottomSheetDialog.dismiss()
                }
            }
        }
    }

    override fun updateUI(invoice: Invoice) {
        id = invoice.id
        dueDate = invoice.due_date
        idTransactionType = invoice.id_transaction_type
        val idStatus = invoice.id_status
        costumer = invoice.costumer
        supplier = invoice.supplier
        tax = invoice.tax
        total = invoice.total

        invoiceDetailsTxtId.text = id
        invoiceDetailsTxtDueDate.text = MyDateFormatter.stringToDateMonthYearBahasa(dueDate!!)

        when (idStatus){
            0->{
                invoiceDetailsTxtStatus.text = InvoiceStatus.DEBT.status
                invoiceDetailsTxtStatus.setTextColor(ContextCompat.getColor(this@InvoiceDetailsActivity,
                        R.color.colorTextBlack))
                invoiceDetailsTxtStatus.setBackgroundResource(R.drawable.bg_invoice_debt)
            }
            1->{
                invoiceDetailsTxtStatus.text = InvoiceStatus.PAID.status
                invoiceDetailsTxtStatus.setTextColor(ContextCompat.getColor(this@InvoiceDetailsActivity,
                        R.color.colorTextWhite))
                invoiceDetailsTxtStatus.setBackgroundResource(R.drawable.bg_round_invoice_paid)
            }
            2->{
                invoiceDetailsTxtStatus.text = InvoiceStatus.DRAFT.status
                invoiceDetailsTxtStatus.setTextColor(ContextCompat.getColor(this@InvoiceDetailsActivity,
                        R.color.colorTextWhite))
                invoiceDetailsTxtStatus.setBackgroundResource(R.drawable.bg_invoice_draft)
            }
        }

        val idTransactionType = invoice.id_transaction_type
        when(idTransactionType){
            0->{ invoiceDetailsTxtName.text = invoice.costumer }
            1->{ invoiceDetailsTxtName.text = invoice.supplier }
            2->{ invoiceDetailsTxtName.text = invoice.supplier }
        }

        invoiceDetailsTxtSubTotal.text = MyCurrencyFormat.rupiah(total!!)
        val taxPercent = "$tax%"
        invoiceDetailsTxtTaxPercent.text = taxPercent
        invoiceDetailsTxtTotal.text = MyCurrencyFormat.rupiah(total!!)
        invoiceDetailsTxtTax.text = MyCurrencyFormat.rupiah(0)
        invoiceDetailsTxtPaid.text = MyCurrencyFormat.rupiah(0)
        invoiceDetailsTxtDueAmount.text = MyCurrencyFormat.rupiah(total!!)

        //FAKE ITEM
        val productList: MutableList<Product> = ArrayList()
        val product1 = Product(
                1,
                "Paprika Merah",
                20000,
                35000,
                "Harga per Kg",
                "2018-03-26 11:43:00",
                "2018-03-26 11:43:00")
        val product2 = Product(
                2,
                "Paprika Hijau",
                15000,
                25000,
                "Harga per Kg",
                "2018-03-26 11:43:00",
                "2018-03-26 11:43:00")
        val product3 = Product(
                3,
                "Tomat Cherry",
                10000,
                15000,
                "Harga per Kg",
                "2018-03-26 11:43:00",
                "2018-03-26 11:43:00")
        val product4 = Product(
                4,
                "Lolorosa",
                18000,
                25000,
                "Harga per Kg",
                "2018-03-26 11:43:00",
                "2018-03-26 11:43:00")
        val product5 = Product(
                5,
                "Butterhead",
                8000,
                15000,
                "Harga per Kg",
                "2018-03-26 11:43:00",
                "2018-03-26 11:43:00")
        val producta1 = Product(
                1,
                "Paprika Merah",
                20000,
                35000,
                "Harga per Kg",
                "2018-03-26 11:43:00",
                "2018-03-26 11:43:00")
        val producta2 = Product(
                2,
                "Paprika Hijau",
                15000,
                25000,
                "Harga per Kg",
                "2018-03-26 11:43:00",
                "2018-03-26 11:43:00")
        val producta3 = Product(
                3,
                "Tomat Cherry",
                10000,
                15000,
                "Harga per Kg",
                "2018-03-26 11:43:00",
                "2018-03-26 11:43:00")
        val producta4 = Product(
                4,
                "Lolorosa",
                18000,
                25000,
                "Harga per Kg",
                "2018-03-26 11:43:00",
                "2018-03-26 11:43:00")
        val producta5 = Product(
                5,
                "Butterhead",
                8000,
                15000,
                "Harga per Kg",
                "2018-03-26 11:43:00",
                "2018-03-26 11:43:00")
        productList.add(product1)
        productList.add(product2)
        productList.add(product3)
        productList.add(product4)
        productList.add(product5)
        productList.add(producta1)
        productList.add(producta2)
        productList.add(producta3)
        productList.add(producta4)
        productList.add(producta5)
        productList.add(product1)
        productList.add(product2)
        productList.add(product3)
        productList.add(product4)
        productList.add(product5)

        val adapterRvItemList = InvoiceItemListAdapter(productList)
        val lmRvItemList = LinearLayoutManager(this@InvoiceDetailsActivity)
        invoiceDetailsRvItem.adapter = adapterRvItemList
        invoiceDetailsRvItem.layoutManager = lmRvItemList
        invoiceDetailsRvItem.setHasFixedSize(true)
    }

    companion object {
        const val INTENT_DATA_INVOICE = "IntentDataInvoice"
    }
}

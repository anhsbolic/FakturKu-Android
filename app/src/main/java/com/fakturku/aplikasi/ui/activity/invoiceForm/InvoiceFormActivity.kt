package com.fakturku.aplikasi.ui.activity.invoiceForm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.utils.MyDateFormatter
import kotlinx.android.synthetic.main.activity_invoice_form.*
import java.util.*

class InvoiceFormActivity : AppCompatActivity(), InvoiceFormContract.View {

    private lateinit var presenter: InvoiceFormPresenter

    private lateinit var idTransaction: String
    private var transactionMode: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoice_form)
        title = null
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_clear)

        //Init Presenter
        presenter = InvoiceFormPresenter(this@InvoiceFormActivity)

        //Set Date
        val dateNow = Date()
        val strDateNow = MyDateFormatter.dateToDateMonthYearBahasa(dateNow)
        invoiceFormTxtDueDate.text = strDateNow

        //Get ID
        if (intent.hasExtra(INTENT_TRANSACTION_MODE)) {
            transactionMode = intent.getIntExtra(INTENT_TRANSACTION_MODE, 0)
            updateUI(transactionMode, dateNow)
        }

    }

    private fun updateUI(transactionMode: Int, date: Date) {
        val id = when(transactionMode){
            MODE_SALES ->{ "#S" }
            MODE_BUY ->{ "#B" }
            MODE_COST ->{ "#C" }
            else ->{ "" }
        }

        val strDate = MyDateFormatter.dateToYMDHM(date)
        idTransaction = "$id$strDate"
        invoiceFormTxtId.text = idTransaction
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                onBackPressed()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this@InvoiceFormActivity)
                .setMessage(R.string.invoice_form_backed_message)
                .setPositiveButton(R.string.invoice_form_backed_positive,{ _ , _ ->
                    super.onBackPressed()
                })
                .setNegativeButton(R.string.invoice_form_backed_negative, null)
                .show()
    }

    companion object {
        const val INTENT_TRANSACTION_MODE = "TransactionMode"

        const val MODE_SALES = 0
        const val MODE_BUY = 1
        const val MODE_COST = 2

    }
}

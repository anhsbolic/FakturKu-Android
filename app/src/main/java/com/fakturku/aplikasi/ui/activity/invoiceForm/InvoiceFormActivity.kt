package com.fakturku.aplikasi.ui.activity.invoiceForm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.Toast
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.utils.MyDateFormatter
import kotlinx.android.synthetic.main.activity_invoice_form.*
import java.util.*

class InvoiceFormActivity : AppCompatActivity(), InvoiceFormContract.View {

    private lateinit var presenter: InvoiceFormPresenter

    private lateinit var idTransaction: String
    private var transactionType: Int = 0

    private lateinit var dueDate: Date
    private lateinit var strDueDate: String

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
        dueDate = Date()
        presenter.updateDueDate(dueDate)

        //Get ID
        if (intent.hasExtra(INTENT_TRANSACTION_MODE)) {
            transactionType = intent.getIntExtra(INTENT_TRANSACTION_MODE, 0)
            val currentDate = Date()
            presenter.updateTransactionId(transactionType, currentDate)
            presenter.updateItemType(transactionType)
        }

        //Get Date
        invoiceFormBtnDueDate.setOnClickListener {
            val builder = AlertDialog.Builder(this@InvoiceFormActivity)
            val myDatePicker = DatePicker(this@InvoiceFormActivity)
            val currentYear = MyDateFormatter.getYearFromDate(dueDate)
            val currentMonth = MyDateFormatter.getMonthFromDate(dueDate)
            val currentDay = MyDateFormatter.getDayOfMonthFromDate(dueDate)
            myDatePicker.updateDate(currentYear, currentMonth, currentDay)
            builder.setView(myDatePicker)
            builder.setPositiveButton("Set Tanggal", { _ , _ ->
                val selectedDay = myDatePicker.dayOfMonth
                val selectedMonth = myDatePicker.month
                val selectedYear = myDatePicker.year

                if (selectedYear > currentYear) {
                    dueDate = MyDateFormatter.getDate(selectedDay, selectedMonth, selectedYear)
                    presenter.updateDueDate(dueDate)
                } else if (selectedYear == currentYear) {
                    if (selectedMonth > currentMonth){
                        dueDate = MyDateFormatter.getDate(selectedDay, selectedMonth, selectedYear)
                        presenter.updateDueDate(dueDate)
                    } else if (selectedMonth == currentMonth) {
                        if (selectedDay >= currentDay){
                            dueDate = MyDateFormatter.getDate(selectedDay, selectedMonth, selectedYear)
                            presenter.updateDueDate(dueDate)
                        } else {
                            showDueDateError(strDueDate)
                        }
                    } else {
                        showDueDateError(strDueDate)
                    }
                } else {
                    showDueDateError(strDueDate)
                }
            })
            builder.show()
        }

        //Add Costumer or Supplier
        invoiceFormBtnAddPerson.setOnClickListener {
            presenter.addPerson()
        }
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

    override fun updateTransactionId(strTransactionId: String) {
        idTransaction = strTransactionId
        invoiceFormTxtId.text = strTransactionId
    }

    override fun updateItemTitle(strItemTitle: String) {
        invoiceFormTxtItemTitle.text = strItemTitle
    }

    override fun updateDueDate(strDueDate: String) {
        this.strDueDate = strDueDate
        invoiceFormTxtDueDate.text = strDueDate
    }

    private fun showDueDateError(strDueDate: String){
        Toast.makeText(this@InvoiceFormActivity,"Pilih setelah tanggal $strDueDate",
                Toast.LENGTH_SHORT).show()
    }

    override fun showAddPersonPage() {

    }

    companion object {
        const val INTENT_TRANSACTION_MODE = "TransactionMode"

        const val SALES_TRANSACTION = 0
        const val BUY_TRANSACTION = 1
        const val COST_TRANSACTION = 2
    }
}

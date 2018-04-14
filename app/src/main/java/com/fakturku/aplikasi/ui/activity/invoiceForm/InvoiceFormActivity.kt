package com.fakturku.aplikasi.ui.activity.invoiceForm

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.Toast
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.model.Product
import com.fakturku.aplikasi.ui.activity.search.SearchActivity
import com.fakturku.aplikasi.ui.adapter.AddItemListAdapter
import com.fakturku.aplikasi.utils.MyDateFormatter
import kotlinx.android.synthetic.main.activity_invoice_form.*
import java.util.*

class InvoiceFormActivity : AppCompatActivity(), InvoiceFormContract.View {

    private lateinit var presenter: InvoiceFormPresenter

    private lateinit var idTransaction: String
    private var transactionType: Int = 0

    private lateinit var dueDate: Date
    private lateinit var currentDate: Date
    private lateinit var strCurrentDate: String
    private var currentYear: Int = 0
    private var currentMonth: Int = 0
    private var currentDay: Int = 0

    private var dataItemList: MutableList<Product> = ArrayList()
    private lateinit var adapterRvItemList: RecyclerView.Adapter<*>
    private lateinit var lmRvItemList: LinearLayoutManager
    private lateinit var animator: DefaultItemAnimator
    private lateinit var dividerItemDecoration: DividerItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoice_form)
        title = null
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_clear)

        //Init Presenter
        presenter = InvoiceFormPresenter(this@InvoiceFormActivity)

        //Set Current Date
        currentDate = Date()
        presenter.updateDueDate(currentDate)
        strCurrentDate = MyDateFormatter.dateToDateMonthYearBahasa(currentDate)
        currentYear = MyDateFormatter.getYearFromDate(currentDate)
        currentMonth = MyDateFormatter.getMonthFromDate(currentDate)
        currentDay = MyDateFormatter.getDayOfMonthFromDate(currentDate)

        //Set Due Date
        dueDate = currentDate

        //Get ID
        if (intent.hasExtra(INTENT_TRANSACTION_MODE)) {
            transactionType = intent.getIntExtra(INTENT_TRANSACTION_MODE, 0)
            presenter.updateTransactionId(transactionType, currentDate)
            presenter.updateItemType(transactionType)
        }

        //Get Date
        invoiceFormBtnDueDate.setOnClickListener {
            val builder = AlertDialog.Builder(this@InvoiceFormActivity)
            val myDatePicker = DatePicker(this@InvoiceFormActivity)
            val dueDateYear = MyDateFormatter.getYearFromDate(dueDate)
            val dueDateMonth = MyDateFormatter.getMonthFromDate(dueDate)
            val dueDateDay = MyDateFormatter.getDayOfMonthFromDate(dueDate)
            myDatePicker.updateDate(dueDateYear, dueDateMonth, dueDateDay)
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
                            showDueDateError(strCurrentDate)
                        }
                    } else {
                        showDueDateError(strCurrentDate)
                    }
                } else {
                    showDueDateError(strCurrentDate)
                }
            })
            builder.show()
        }

        //Add Costumer or Supplier
        invoiceFormBtnAddPerson.setOnClickListener {
            presenter.addPerson()
        }

        //Init RecyclerView Item List
//        val product1 = Product(
//                "1",
//                "Paprika Merah",
//                20000,
//                35000,
//                "Harga per Kg",
//                "2018-03-26 11:43:00",
//                "2018-03-26 11:43:00")
//        dataItemList.add(product1)
//        dataItemList.add(product1)
//        dataItemList.add(product1)

        adapterRvItemList = AddItemListAdapter(dataItemList)
        lmRvItemList= LinearLayoutManager(this@InvoiceFormActivity)
        animator = DefaultItemAnimator()
        animator.addDuration = 300
        animator.removeDuration = 600

        dividerItemDecoration = DividerItemDecoration(this@InvoiceFormActivity,
                DividerItemDecoration.VERTICAL)
        invoiceFormRvItem.adapter = adapterRvItemList
        invoiceFormRvItem.layoutManager = lmRvItemList
        invoiceFormRvItem.itemAnimator = animator
        invoiceFormRvItem.addItemDecoration(dividerItemDecoration)
        invoiceFormRvItem.setHasFixedSize(false)
        invoiceFormRvItem.setItemViewCacheSize(dataItemList.size)

        //Add Item
        invoiceFormTxtAddItem.setOnClickListener {
            presenter.addItem()
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
        invoiceFormTxtDueDate.text = strDueDate
    }

    private fun showDueDateError(strDueDate: String){
        Toast.makeText(this@InvoiceFormActivity,"Pilih setelah tanggal $strDueDate",
                Toast.LENGTH_SHORT).show()
    }

    override fun showAddPersonPage() {
        val intentAddPersonPage = Intent(this@InvoiceFormActivity, SearchActivity::class.java)
        intentAddPersonPage.putExtra(SearchActivity.INTENT_SEARCH_MODE, SearchActivity.SEARCH_COSTUMER)
        startActivityForResult(intentAddPersonPage, INTENT_ADD_COSTUMER)
    }


    override fun showAddItemPage() {
        val intentAddItemPage = Intent(this@InvoiceFormActivity, SearchActivity::class.java)
        intentAddItemPage.putExtra(SearchActivity.INTENT_SEARCH_MODE, SearchActivity.SEARCH_PRODUCT)
        startActivityForResult(intentAddItemPage, INTENT_ADD_PRODUCT)
    }

    override fun addSelectedItem(product: Product) {
        dataItemList.add(product)
        adapterRvItemList.notifyItemInserted(dataItemList.lastIndex)
        invoiceFormRvItem.setItemViewCacheSize(dataItemList.size)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            INTENT_ADD_COSTUMER->{
                when (resultCode) {
                    INTENT_ADD_COSTUMER_SUCCESS -> {
                        if (data != null) {
                            val name: String = data.extras.getString(INTENT_ADD_COSTUMER_DATA)
                            invoiceFormTxtName.text = name
                        }
                    }
                }
            }
            INTENT_ADD_PRODUCT->{
                when (resultCode) {
                    INTENT_ADD_PRODUCT_SUCCESS -> {
                        if (data != null) {
                            Log.d("TES", "ADA")
                            val product: Product = data.extras.getParcelable(INTENT_ADD_PRODUCT_DATA)
                            presenter.addSelectedItem(product)
                        } else{
                            Log.d("TES", "GA ADA")
                        }
                    }
                }
            }
            else -> {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    companion object {
        const val INTENT_TRANSACTION_MODE = "TransactionMode"

        const val SALES_TRANSACTION = 0
        const val BUY_TRANSACTION = 1
        const val COST_TRANSACTION = 2

        const val INTENT_ADD_COSTUMER = 10
        const val INTENT_ADD_COSTUMER_SUCCESS = 11
        const val INTENT_ADD_COSTUMER_DATA: String = "AddCostumerData"

        const val INTENT_ADD_PRODUCT = 20
        const val INTENT_ADD_PRODUCT_SUCCESS = 21
        const val INTENT_ADD_PRODUCT_DATA: String = "AddProductData"


    }
}

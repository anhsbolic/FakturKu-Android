package com.fakturku.aplikasi.ui.activity.productForm

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.model.Product
import com.fakturku.aplikasi.ui.fragment.productFragment.ProductFragment
import kotlinx.android.synthetic.main.activity_product_form.*

class ProductFormActivity : AppCompatActivity(), ProductFormContract.View {

    private var userId: Long = 0
    private lateinit var presenter: ProductFormPresenter
    private var isUpdateProductMode: Boolean = false
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_form)
        title = null
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_clear)

        //Init Presenter
        presenter = ProductFormPresenter(this@ProductFormActivity)

        //Get User Id
        if (intent.hasExtra(INTENT_USER_ID)) {
            userId = intent.getLongExtra(INTENT_USER_ID,0)
        }

        //check intent data & mode
        if (intent.hasExtra(INTENT_PRODUCT_DATA)) {
            product = intent.getParcelableExtra(INTENT_PRODUCT_DATA)
            presenter.setUpdateMode(product)
        }

        //UI handling & listener
        productFormBtnSave.setOnClickListener {
            val name = productFormName.text.toString()
            val buyPrice = productFormBuyPrice.text.toString()
            val sellPrice = productFormSellPrice.text.toString()
            val notes = productFormNotes.text.toString()

            var intBuyPrice: Int? = null
            if (buyPrice.isNotEmpty()){
                intBuyPrice = buyPrice.toInt()
            }

            var intSellPrice: Int? = null
            if (sellPrice.isNotEmpty()){
                intSellPrice = sellPrice.toInt()
            }

            if (!isUpdateProductMode) {
                presenter.addProduct(userId, null, name, intBuyPrice, intSellPrice, notes,
                        null, null, isUpdateProductMode)
            } else {
                presenter.updateProduct(userId, product.id, name, intBuyPrice, intSellPrice, notes,
                        product.created_at, null, isUpdateProductMode)
            }
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
        AlertDialog.Builder(this@ProductFormActivity)
                .setMessage(R.string.product_form_backed_message)
                .setPositiveButton(R.string.product_form_backed_positive,{ _ , _ ->
                    super.onBackPressed()
                })
                .setNegativeButton(R.string.product_form_backed_negative, null)
                .show()
    }

    override fun setUpdateMode(product: Product) {
        isUpdateProductMode = true

        //Update UI
        productFormName.setText(product.name)
        productFormBuyPrice.setText(product.purchase_price.toString())
        productFormSellPrice.setText(product.sell_price.toString())
        productFormNotes.setText(product.info)
        val strUpdate = "Update"
        productFormBtnSave.text = strUpdate
    }

    override fun showErrorInput(isNameValid: Boolean) {
        if (!isNameValid){
            productFormName.error = "Isi dengan Nama Product"
        } else {
            productFormName.error = null
        }
    }

    override fun showAddProductSuccess(product: Product) {
        val msg = "${product.name} berhasil ditambahkan"
        Snackbar.make(productFormCoordinatorLayout, msg, Snackbar.LENGTH_SHORT).show()

        Handler().postDelayed({
            val intentAddCostumerSuccess = Intent()
            setResult(ProductFragment.INTENT_ADD_PRODUCT_SUCCESS, intentAddCostumerSuccess)
            finish()
        }, 1200)
    }

    override fun showUpdateProductSuccess(product: Product) {
        val msg = "${product.name} berhasil diupdate"
        Snackbar.make(productFormCoordinatorLayout, msg, Snackbar.LENGTH_SHORT).show()

        Handler().postDelayed({
            val intentUpdateCostumerSuccess = Intent()
            setResult(ProductFragment.INTENT_UPDATE_PRODUCT_SUCCESS, intentUpdateCostumerSuccess)
            finish()
        }, 1200)
    }

    companion object {
        const val INTENT_USER_ID: String = "IntentUserId"
        const val INTENT_PRODUCT_DATA: String = "IntentProductData"
    }
}

package com.fakturku.aplikasi.ui.activity.productDetails

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.model.Product
import com.fakturku.aplikasi.ui.fragment.productFragment.ProductFragment
import com.fakturku.aplikasi.utils.MyCurrencyFormat
import kotlinx.android.synthetic.main.activity_product_details.*

class ProductDetailsActivity : AppCompatActivity(), ProductDetailsContract.View {

    private lateinit var presenter: ProductDetailsPresenter
    private var userId: Long = 0
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        title = null
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        //init presenter
        presenter = ProductDetailsPresenter(this@ProductDetailsActivity)

        //Get User Id
        if (intent.hasExtra(INTENT_USER_ID)) {
            userId = intent.getLongExtra(INTENT_USER_ID, 0)
        }

        //Get Intent Data
        if (intent.hasExtra(INTENT_DATA_PRODUCT)) {
            product = intent.getParcelableExtra(INTENT_DATA_PRODUCT)
            title = product.name
            setProductData(product)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_product_details_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                onBackPressed()
                true
            }

            R.id.productDetailsMenuUpdate->{
                presenter.update(product)
                return true
            }

            R.id.productDetailsMenuDelete->{
                val strMsg = "Anda yakin akan menghapus ${product.name} dari database ?"
                val yesMsg = "Ya, Hapus"
                val noMsg = "Tidak"
                AlertDialog.Builder(this@ProductDetailsActivity)
                        .setMessage(strMsg)
                        .setPositiveButton(yesMsg,{ _ , _ ->
                            presenter.delete(userId, product)
                        })
                        .setNegativeButton(noMsg, null)
                        .show()
                return true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun setProductData(product: Product) {
        productDetailsName.text = product.name
        val buyPrice = product.purchase_price
        if (buyPrice != null){
            productDetailsBuyPrice.text = MyCurrencyFormat.rupiah(buyPrice)
        }
        val sellPrice = product.sell_price
        if (sellPrice != null){
            productDetailsSellPrice.text = MyCurrencyFormat.rupiah(sellPrice)
        }
        productDetailsNotes.text = product.info
    }

    override fun update(product: Product) {
        val intentUpdate = Intent()
        intentUpdate.putExtra(ProductFragment.INTENT_PRODUCT_DETAILS_UPDATE_DATA, product)
        setResult(ProductFragment.INTENT_PRODUCT_DETAILS_UPDATE, intentUpdate)
        finish()
    }

    override fun delete(product: Product) {
        val intentDelete = Intent()
        setResult(ProductFragment.INTENT_PRODUCT_DETAILS_DELETE, intentDelete)
        finish()
    }

    companion object {
        const val INTENT_USER_ID: String = "IntentUserId"
        const val INTENT_DATA_PRODUCT: String = "IntentDataProduct"
    }
}

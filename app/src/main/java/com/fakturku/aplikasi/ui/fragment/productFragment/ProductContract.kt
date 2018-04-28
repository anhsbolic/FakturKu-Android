package com.fakturku.aplikasi.ui.fragment.productFragment

import com.fakturku.aplikasi.model.Product

interface ProductContract{

    interface View{
        fun initRecyclerView()

        fun showProgress()

        fun hideProgress()

        fun showPlaceholder()

        fun hidePlaceholder()

        fun showProductList(productList: MutableList<Product>)

        fun setTotalPage(totalPage: Int)

        fun showProductDetails(product: Product)

        fun openAddProductPage()

        fun openUpdateProductPage(product: Product)
    }

    interface Presenter{
        fun loadProductListData(page: Int)

        fun addProduct()

        fun updateProduct(product: Product)

        fun seeProductDetails(product: Product)
    }
}
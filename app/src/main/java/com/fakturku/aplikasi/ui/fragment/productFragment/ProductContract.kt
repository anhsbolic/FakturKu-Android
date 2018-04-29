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

        fun showProductDetails(userId: Long, product: Product)

        fun openAddProductPage(userId: Long)

        fun openUpdateProductPage(userId: Long, product: Product)
    }

    interface Presenter{
        fun loadProductListData(userId: Long, page: Int)

        fun addProduct(userId: Long)

        fun updateProduct(userId: Long, product: Product)

        fun seeProductDetails(userId: Long, product: Product)
    }
}
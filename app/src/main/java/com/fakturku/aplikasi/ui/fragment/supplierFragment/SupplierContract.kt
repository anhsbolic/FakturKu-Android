package com.fakturku.aplikasi.ui.fragment.supplierFragment

import com.fakturku.aplikasi.model.Supplier

interface SupplierContract{

    interface View{
        fun initRecyclerView()

        fun showProgress()

        fun hideProgress()

        fun showPlaceholder()

        fun hidePlaceholder()

        fun showSupplierList(supplierList: MutableList<Supplier>)

        fun showSupplierDetails(supplier: Supplier)

        fun openAddSupplierPage()

        fun openUpdateSupplierPage(supplier: Supplier)

    }

    interface Presenter{
        fun loadSupplierListData(page: Int)

        fun addSupplier()

        fun updateSupplier(supplier: Supplier)

        fun seeSupplierDetails(supplier: Supplier)
    }
}
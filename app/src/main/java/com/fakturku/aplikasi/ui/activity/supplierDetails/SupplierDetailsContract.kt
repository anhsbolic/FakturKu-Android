package com.fakturku.aplikasi.ui.activity.supplierDetails

import com.fakturku.aplikasi.model.Supplier

interface SupplierDetailsContract{

    interface View{
        fun setSupplierData(supplier: Supplier)

        fun delete(supplier: Supplier)

        fun update(supplier: Supplier)

    }

    interface Presenter{
        fun delete(supplier: Supplier)

        fun update(supplier: Supplier)
    }
}
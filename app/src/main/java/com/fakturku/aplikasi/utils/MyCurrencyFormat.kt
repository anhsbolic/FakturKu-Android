package com.fakturku.aplikasi.utils

import java.text.NumberFormat
import java.util.*

object MyCurrencyFormat {

    fun rupiah(intRupiah: Int): String {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)

        return formatRupiah.format(intRupiah.toDouble())
    }
}
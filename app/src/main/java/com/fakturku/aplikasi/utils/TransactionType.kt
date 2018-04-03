package com.fakturku.aplikasi.utils

enum class TransactionType(val id: Int, val type: String) {
    SELL(0, "Penjualan"),
    BUY(1, "Pembelian"),
    COST(2, "Biaya")
}
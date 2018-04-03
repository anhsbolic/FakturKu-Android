package com.fakturku.aplikasi.utils

enum class InvoiceStatus(val id: Int, val status: String) {
    DEBT(0, "Belum Lunas"),
    PAID(1, "Lunas"),
    DRAFT(2, "Draft")
}
package com.fakturku.aplikasi.utils

import java.text.SimpleDateFormat
import java.util.*

object MyDateFormatter {

    /* DAY NAME IN BAHASA */
    private const val SUNDAY = "Sunday"
    private const val MONDAY = "Monday"
    private const val TUESDAY = "Tuesday"
    private const val WEDNESDAY = "Wednesday"
    private const val THURSDAY = "Thursday"
    private const val FRIDAY = "Friday"
    private const val SATURDAY = "Saturday"
    private const val SUNDAY_BAHASA = "Minggu"
    private const val MONDAY_BAHASA = "Senin"
    private const val TUESDAY_BAHASA = "Selasa"
    private const val WEDNESDAY_BAHASA = "Rabu"
    private const val THURSDAY_BAHASA = "Kamis"
    private const val FRIDAY_BAHASA = "Jumat"
    private const val SATURDAY_BAHASA = "Sabtu"

    /* MONTH NUMBER */
    private const val JANUARY_NUMBER = "01"
    private const val FEBRUARY_NUMBER = "02"
    private const val MARCH_NUMBER = "03"
    private const val APRIL_NUMBER = "04"
    private const val MAY_NUMBER = "05"
    private const val JUNE_NUMBER = "06"
    private const val JULY_NUMBER = "07"
    private const val AUGUST_NUMBER = "08"
    private const val SEPTEMBER_NUMBER = "09"
    private const val OCTOBER_NUMBER = "10"
    private const val NOVEMBER_NUMBER = "11"
    private const val DECEMBER_NUMBER = "12"

    /* MONTH NAME IN BAHASA */
    private const val JANUARY_BAHASA = "Januari"
    private const val FEBRUARY_BAHASA = "Februari"
    private const val MARCH_BAHASA = "Maret"
    private const val APRIL_BAHASA = "April"
    private const val MAY_BAHASA = "Mei"
    private const val JUNE_BAHASA = "Juni"
    private const val JULY_BAHASA = "Juli"
    private const val AUGUST_BAHASA = "Agustus"
    private const val SEPTEMBER_BAHASA = "September"
    private const val OCTOBER_BAHASA = "Oktober"
    private const val NOVEMBER_BAHASA = "November"
    private const val DECEMBER_BAHASA = "Desember"

    fun getDate(day: Int, month: Int, year: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, day)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.YEAR, year)
        val date = calendar.time
        calendar.clear()

        return date
    }

    fun stringToDateMonthYearBahasa(strDate: String): String {
        //Month Formatter
        val strYear = (strDate[0].toString() + strDate[1].toString()
                + strDate[2].toString() + strDate[3].toString())
        val strMonth = strDate[5].toString() + strDate[6].toString()
        val strDayNumber = strDate[8].toString() + strDate[9].toString()

        var strMonthName = ""
        when (strMonth) {
            JANUARY_NUMBER -> strMonthName = JANUARY_BAHASA
            FEBRUARY_NUMBER -> strMonthName = FEBRUARY_BAHASA
            MARCH_NUMBER -> strMonthName = MARCH_BAHASA
            APRIL_NUMBER -> strMonthName = APRIL_BAHASA
            MAY_NUMBER -> strMonthName = MAY_BAHASA
            JUNE_NUMBER -> strMonthName = JUNE_BAHASA
            JULY_NUMBER -> strMonthName = JULY_BAHASA
            AUGUST_NUMBER -> strMonthName = AUGUST_BAHASA
            SEPTEMBER_NUMBER -> strMonthName = SEPTEMBER_BAHASA
            OCTOBER_NUMBER -> strMonthName = OCTOBER_BAHASA
            NOVEMBER_NUMBER -> strMonthName = NOVEMBER_BAHASA
            DECEMBER_NUMBER -> strMonthName = DECEMBER_BAHASA
        }

        return "$strDayNumber $strMonthName $strYear"
    }
}
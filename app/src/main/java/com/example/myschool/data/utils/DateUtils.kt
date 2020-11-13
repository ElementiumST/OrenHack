package com.example.myschool.data.utils

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*

private val calendar = Calendar.getInstance()

fun getDateString(ms: Long): String {
    calendar.timeInMillis = ms
    return "${calendar.get(Calendar.DAY_OF_MONTH)} " +
            "${monthNumToString(calendar.get(Calendar.MONTH))}, " +
            "${calendar.get(Calendar.YEAR)}"
}
private fun monthNumToString(int: Int): String {
    return when(int) {
        0 -> "января"
        1 -> "февраля"
        2 -> "марта"
        3 -> "апреля"
        4 -> "мая"
        5 -> "июня"
        6 -> "июля"
        7 -> "августа"
        8 -> "сентября"
        9 -> "октября"
        10 -> "ноября"
        11 -> "декабря"
        else -> "N/A"
    }
}
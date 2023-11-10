package br.com.fiap.hr_tech.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun getMonthName(month: Int): String {

    val calendar = Calendar.getInstance()
    calendar.set(Calendar.MONTH, month - 1)

    val dateFormat = SimpleDateFormat("MMMM", Locale.getDefault())

    return dateFormat.format(calendar.time).capitalize()

}
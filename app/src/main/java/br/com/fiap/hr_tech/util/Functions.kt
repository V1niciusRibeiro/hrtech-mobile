package br.com.fiap.hr_tech.util

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Locale

fun getMonthName(month: Int): String {

    val calendar = Calendar.getInstance()
    calendar.set(Calendar.MONTH, month - 1)

    val dateFormat = SimpleDateFormat("MMMM", Locale.getDefault())

    return dateFormat.format(calendar.time).capitalize()

}

fun getHour(localDateTime: LocalDateTime): String {
    val hour =
        if (localDateTime.hour > 9) localDateTime.hour.toString() else "0" + localDateTime.hour.toString()

    val minute =
        if (localDateTime.minute > 9) localDateTime.minute.toString() else "0" + localDateTime.minute.toString()

    return hour + "h" + minute
}
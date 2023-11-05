package br.com.fiap.hr_tech.mvvm.view_model

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.hr_tech.R
import java.time.DayOfWeek
import java.time.LocalDate

class CalendarGridViewModel(month: Int, year: Int) : ViewModel() {

    private val today = LocalDate.now()
    private val month = month
    private val year = year

    val calendar = loadCalendar(month, year)

    private val _dateSelected = MutableLiveData<LocalDate>()
    val dateSelected: LiveData<LocalDate> = _dateSelected

    private fun loadCalendar(month: Int, year: Int): List<LocalDate> {
        var localDate = initiateLocalDate(month, year)
        val calendar = mutableListOf<LocalDate>()
        repeat(35) {
            calendar.add(LocalDate.of(localDate.year, localDate.monthValue, localDate.dayOfMonth))
            localDate = localDate.plusDays(1)
        }
        return calendar
    }


    private fun initiateLocalDate(month: Int, year: Int): LocalDate {
        var localDate = LocalDate.of(year, month, 1)
        while (localDate.dayOfWeek != DayOfWeek.SUNDAY) {
            localDate = localDate.minusDays(1)
        }
        return localDate
    }

    fun dateSelectedChangeValue(month: Int, day: Int) {
        _dateSelected.value = LocalDate.of(year, month, day)
    }

    fun textColor(calendarDate: LocalDate, daySelected: LocalDate?, context: Context): Color {
        var color = Color.Black
        if (calendarDate.monthValue != month) {
            color = Color(context.getColor(R.color.gray))
        }
        if ((today.dayOfMonth == calendarDate.dayOfMonth) and (today.month == calendarDate.month)) {
            color = Color(context.getColor(R.color.blue))
        }
        if (daySelected != null) {
            if ((calendarDate.dayOfMonth == daySelected.dayOfMonth)
                and (calendarDate.month == daySelected.month)
            ) {
                color = Color.White
            }
        }
        return color
    }

    fun borderColor(calendarDate: LocalDate, context: Context): Color {
        var color = Color.Transparent
        if ((today.dayOfMonth == calendarDate.dayOfMonth) and (today.month == calendarDate.month)) {
            color = Color(context.getColor(R.color.blue))
        }
        return color
    }

    fun backgroundColor(calendarDate: LocalDate, daySelected: LocalDate?, context: Context): Color {
        var color = Color.Transparent
        if (daySelected != null) {
            if ((calendarDate.dayOfMonth == daySelected.dayOfMonth)
                and (calendarDate.month == daySelected.month)
            ) {
                color = Color(context.getColor(R.color.blue))
            }
        }
        return color
    }

}
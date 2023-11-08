package br.com.fiap.hr_tech.mvvm.view_model

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.hr_tech.R
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjuster
import java.time.temporal.TemporalAdjusters

class CalendarGridViewModel(private val year: Int, private val month: Int) : ViewModel() {

    private val today = LocalDate.now()

    val calendar = loadCalendar(month, year)

    private val _dateSelected = MutableLiveData<LocalDate?>()
    val dateSelected: LiveData<LocalDate?> = _dateSelected

    private fun loadCalendar(month: Int, year: Int): List<LocalDate> {
        var localDate = LocalDate.of(year, month, 1)
            .with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))

        val calendar = mutableListOf<LocalDate>()
        repeat(35) {
            calendar.add(localDate)
            localDate = localDate.plusDays(1)
        }
        return calendar
    }

    fun dateSelectedChangeValue(localDate: LocalDate?) {
        _dateSelected.value = localDate
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
package br.com.fiap.hr_tech.mvvm.view_model

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.fiap.hr_tech.R
import java.time.DayOfWeek
import java.time.LocalDate

class CalendarGridViewModel(
    private val year: Int,
    private val month: Int,
    val retractCalendar: Boolean
) {

    private val today = LocalDate.now()

    private val _calendar = MutableLiveData<List<LocalDate>>()
    val calendar: LiveData<List<LocalDate>> = _calendar

    private val _dateSelected = MutableLiveData<LocalDate?>()
    val dateSelected: LiveData<LocalDate?> = _dateSelected

    fun calendarLoad(daySelected: LocalDate?){

        var localDate = if (retractCalendar) daySelected else LocalDate.of(year, month, 1)
        while (localDate!!.dayOfWeek != DayOfWeek.SUNDAY){
            localDate = localDate.minusDays(1)
        }

        val repeat = if (retractCalendar) 7 else 35
        val calendar = arrayListOf<LocalDate>()
        repeat(repeat) {
            calendar.add(localDate!!)
            localDate = localDate!!.plusDays(1)
        }

        _calendar.value = calendar
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
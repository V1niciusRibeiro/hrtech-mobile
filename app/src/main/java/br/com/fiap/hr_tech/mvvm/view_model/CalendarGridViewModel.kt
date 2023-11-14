package br.com.fiap.hr_tech.mvvm.view_model

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.fiap.hr_tech.R
import java.time.DayOfWeek
import java.time.LocalDate

class CalendarGridViewModel(private val year: Int, private val month: Int) {

    private val today = LocalDate.now()

    private val _dateSelected = MutableLiveData<LocalDate?>()
    val dateSelected: LiveData<LocalDate?> = _dateSelected

    fun dateSelectedChangeValue(localDate: LocalDate?) {
        _dateSelected.value = localDate
    }

    fun getInitialDate(startMonth: Int, startDay: Int): LocalDate {
        val month = if (startMonth == 0) this.month else startMonth
        var localDate = LocalDate.of(year, month, startDay)
        while (localDate.dayOfWeek != DayOfWeek.SUNDAY) {
            localDate = localDate.minusDays(1)
        }
        return localDate
    }

    fun textColor(itemDate: LocalDate, dateSelected: LocalDate?, context: Context): Color {
        var color = Color.Black
        if (itemDate.monthValue != month) {
            color = Color(context.getColor(R.color.gray))
        }
        if ((today.dayOfMonth == itemDate.dayOfMonth) and (today.month == itemDate.month)) {
            color = Color(context.getColor(R.color.blue))
        }
        if (dateSelected != null) {
            if ((itemDate.dayOfMonth == dateSelected.dayOfMonth)
                and (itemDate.month == dateSelected.month)
            ) {
                color = Color.White
            }
        }
        return color
    }

    fun borderColor(itemDate: LocalDate, context: Context): Color {
        var color = Color.Transparent
        if ((today.dayOfMonth == itemDate.dayOfMonth) and (today.month == itemDate.month)) {
            color = Color(context.getColor(R.color.blue))
        }
        return color
    }

    fun backgroundColor(itemDate: LocalDate, dateSelected: LocalDate?, context: Context): Color {
        var color = Color.Transparent
        if (dateSelected != null) {
            if ((itemDate.dayOfMonth == dateSelected.dayOfMonth)
                and (itemDate.month == dateSelected.month)
            ) {
                color = Color(context.getColor(R.color.blue))
            }
        }
        return color
    }

}
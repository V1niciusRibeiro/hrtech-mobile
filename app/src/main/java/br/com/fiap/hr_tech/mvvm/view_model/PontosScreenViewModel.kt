package br.com.fiap.hr_tech.mvvm.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.time.LocalDate

class PontosScreenViewModel {

    private val _retractCalendar = MutableLiveData<Boolean>()
    val retractCalendar: LiveData<Boolean> = _retractCalendar

    private val _dateSelected = MutableLiveData<LocalDate?>()
    val dateSelected: LiveData<LocalDate?> = _dateSelected

    fun retractCalendarChangeValue(retract: Boolean) {
        _retractCalendar.value = retract
    }

    fun dateSelectedChangeValue(localDate: LocalDate?) {
        _dateSelected.value = localDate
    }

}
package br.com.fiap.hr_tech.mvvm.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.time.LocalDate

class WorkHoursScreenViewModel {

    private val _retractCalendar = MutableLiveData<Boolean>()
    val retractCalendar: LiveData<Boolean> = _retractCalendar

    private val _openPopup = MutableLiveData<Boolean>()
    val openPopup: LiveData<Boolean> = _openPopup

    private val _dateSelected = MutableLiveData<LocalDate?>()
    val dateSelected: LiveData<LocalDate?> = _dateSelected

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    private val _hour = MutableLiveData<String>()
    val hour: LiveData<String> = _hour

    fun retractCalendarChangeValue(retract: Boolean) {
        _retractCalendar.value = retract
    }

    fun openPopupChangeValue(openPopup: Boolean) {
        _openPopup.value = openPopup
    }

    fun dateSelectedChangeValue(localDate: LocalDate?) {
        _dateSelected.value = localDate
        if (localDate == null) {
            retractCalendarChangeValue(false)
        }
    }

    fun descriptionChangeValue(description: String) {
        _description.value = description
    }

    fun hourChangeValue(hour: String) {
        _hour.value = hour
    }

}
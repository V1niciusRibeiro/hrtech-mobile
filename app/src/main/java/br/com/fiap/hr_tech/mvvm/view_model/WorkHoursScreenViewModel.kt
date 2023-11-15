package br.com.fiap.hr_tech.mvvm.view_model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.fiap.hr_tech.R
import java.time.LocalDate

class WorkHoursScreenViewModel {

    private val _retractCalendar = MutableLiveData<Boolean>()
    val retractCalendar: LiveData<Boolean> = _retractCalendar

    private val _openPopup = MutableLiveData<Boolean>()
    val openPopup: LiveData<Boolean> = _openPopup

    private val _dateSelected = MutableLiveData<LocalDate?>()
    val dateSelected: LiveData<LocalDate?> = _dateSelected

    private val _idPopup = MutableLiveData<Int>()
    val idPopup: LiveData<Int> = _idPopup

    private val _titlePopup = MutableLiveData<String>()
    val titlePopup: LiveData<String> = _titlePopup

    private val _descriptionPopup = MutableLiveData<String>()
    val descriptionPopup: LiveData<String> = _descriptionPopup

    private val _hourPopup = MutableLiveData<String>()
    val hourPopup: LiveData<String> = _hourPopup

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

    fun descriptionPopupChangeValue(description: String) {
        _descriptionPopup.value = description
    }

    fun hourPopupChangeValue(hour: String) {
        _hourPopup.value = hour
    }

    fun newPopupRegister(context: Context) {
        _idPopup.value = 0
        _titlePopup.value = context.getString(R.string.insert)
        _descriptionPopup.value = ""
        _hourPopup.value = ""
        _openPopup.value = true
    }

    fun selectPopupRegister(id: Int, context: Context) {
        _idPopup.value = id
        _titlePopup.value = context.getString(R.string.update)
        _descriptionPopup.value = ""
        _hourPopup.value = ""
        _openPopup.value = true
    }

}
package br.com.fiap.hr_tech.mvvm.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.time.LocalDate

class CalendarViewModel() {

    private val _selectorMonthOpen = MutableLiveData<Boolean>()
    val selectorMonthOpen: LiveData<Boolean> = _selectorMonthOpen

    private val _year = MutableLiveData<Int>()
    val year: LiveData<Int> = _year

    private val _month = MutableLiveData<Int>()
    val month: LiveData<Int> = _month

    private val _dateSelected = MutableLiveData<LocalDate?>()
    val dateSelected: LiveData<LocalDate?> = _dateSelected

    fun selectorOpenChangeValue(open: Boolean) {
        _selectorMonthOpen.value = open
    }

    fun yearChangeValue(year: Int) {
        _year.value = year
    }

    fun monthChangeValue(month: Int) {
        _month.value = month
    }

    fun dateSelectedChangeValue(localDate: LocalDate?) {
        _dateSelected.value = localDate
    }

    fun processMonthYearSelectorReturn(monthYearSelectorReturn: MonthYearSelectorReturn) {
        yearChangeValue(monthYearSelectorReturn.year)
        monthChangeValue(monthYearSelectorReturn.month)

        if (monthYearSelectorReturn.click){
            selectorOpenChangeValue(false)
        }
    }

}

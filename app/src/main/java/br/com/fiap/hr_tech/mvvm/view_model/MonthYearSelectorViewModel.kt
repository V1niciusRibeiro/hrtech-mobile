package br.com.fiap.hr_tech.mvvm.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class MonthYearSelectorReturn(
    val year: Int,
    val month: Int,
    val click: Boolean
)

class MonthYearSelectorViewModel() {

    private val _year = MutableLiveData<Int>()
    val year: LiveData<Int> = _year

    private val _yearSelected = MutableLiveData<Int>()
    val yearSelected: LiveData<Int> = _yearSelected

    private val _monthSelected = MutableLiveData<Int>()
    val monthSelected: LiveData<Int> = _monthSelected

    private val _click = MutableLiveData<Boolean>()
    val click: LiveData<Boolean> = _click

    fun yearChangeValue(year: Int) {
        _year.value = year
    }

    fun clickChangeValue(click: Boolean) {
        _click.value = click
    }

    fun yearSelectedChangeValue(yearSelected: Int) {
        _yearSelected.value = yearSelected
    }

    fun monthSelectedChangeValue(monthSelected: Int) {
        _monthSelected.value = monthSelected
    }

}
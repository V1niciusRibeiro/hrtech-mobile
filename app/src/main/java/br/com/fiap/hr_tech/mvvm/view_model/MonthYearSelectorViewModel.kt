package br.com.fiap.hr_tech.mvvm.view_model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.fiap.hr_tech.R

class MonthYearSelectorViewModel(context: Context) {

    private val _year = MutableLiveData<Int>()
    val year: LiveData<Int> = _year

    private val _yearSelected = MutableLiveData<Int>()
    val yearSelected: LiveData<Int> = _yearSelected

    private val _monthSelected = MutableLiveData<Int>()
    val monthSelected: LiveData<Int> = _monthSelected

    private val _daySelected = MutableLiveData<Int>()
    val daySelected: LiveData<Int> = _daySelected

    val months = arrayOf(
        context.getString(R.string.january_reduced),
        context.getString(R.string.february_reduced),
        context.getString(R.string.march_reduced),
        context.getString(R.string.april_reduced),
        context.getString(R.string.may_reduced),
        context.getString(R.string.june_reduced),
        context.getString(R.string.july_reduced),
        context.getString(R.string.august_reduced),
        context.getString(R.string.september_reduced),
        context.getString(R.string.october_reduced),
        context.getString(R.string.november_reduced),
        context.getString(R.string.december_reduced)
    )

    fun yearChangeValue(year: Int) {
        _year.value = year
    }

    fun daySelectedChangeValue(daySelected: Int) {
        _daySelected.value = daySelected
    }

    fun yearSelectedChangeValue(yearSelected: Int) {
        _yearSelected.value = yearSelected
    }

    fun monthSelectedChangeValue(monthSelected: Int) {
        _monthSelected.value = monthSelected
    }

}
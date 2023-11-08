package br.com.fiap.hr_tech.mvvm.view_model

import android.content.Context
import androidx.compose.foundation.layout.ColumnScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.fiap.hr_tech.R

class CalendarViewModel(context: Context) {

    private val _selectorMonthOpen = MutableLiveData<Boolean>()
    val selectorMonthOpen: LiveData<Boolean> = _selectorMonthOpen

    private val _year = MutableLiveData<Int>()
    val year: LiveData<Int> = _year

    private val _month = MutableLiveData<Int>()
    val month: LiveData<Int> = _month

    val months = arrayOf(
        context.getString(R.string.january),
        context.getString(R.string.february),
        context.getString(R.string.march),
        context.getString(R.string.april),
        context.getString(R.string.may),
        context.getString(R.string.june),
        context.getString(R.string.july),
        context.getString(R.string.august),
        context.getString(R.string.september),
        context.getString(R.string.october),
        context.getString(R.string.november),
        context.getString(R.string.december)
    )

    fun selectorOpenChangeValue(open: Boolean) {
        _selectorMonthOpen.value = open
    }

    fun yearChangeValue(year: Int) {
        _year.value = year
    }

    fun monthChangeValue(month: Int) {
        _month.value = month
    }

}

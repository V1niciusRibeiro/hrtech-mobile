package br.com.fiap.hr_tech.mvvm.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel


class CalendarGridViewModel {

    private val _daySelected = MutableLiveData<Int>()
    val daySelected: LiveData<Int> = _daySelected

    fun daySelectedChanged(daySelected: Int) {
        _daySelected.value = daySelected
    }

}
package br.com.fiap.hr_tech.mvvm.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class AppHeaderViewModel {

    private val _sideBarOpen = MutableLiveData<Boolean>()
    val sideBarOpen: LiveData<Boolean> = _sideBarOpen

    fun sideBarOpenChengeValue(open: Boolean){
        _sideBarOpen.value = open
    }

}
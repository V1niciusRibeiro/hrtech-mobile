package br.com.fiap.hr_tech.mvvm.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LoginScreenViewModel {

    private val _login = MutableLiveData<String>()
    val login: LiveData<String> = _login

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    fun loginChangeValue(login: String) {
        _login.value = login
    }

    fun passwordChangeValue(password: String) {
        _password.value = password
    }

}
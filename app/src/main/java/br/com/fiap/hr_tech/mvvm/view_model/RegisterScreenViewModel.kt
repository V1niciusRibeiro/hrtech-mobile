package br.com.fiap.hr_tech.mvvm.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class RegisterScreenViewModel {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _confirmPassword = MutableLiveData<String>()
    val confirmPassword: LiveData<String> = _confirmPassword

    fun nameChangeValue(name: String) {
        _name.value = name
    }

    fun emailChangeValue(email: String) {
        _email.value = email
    }

    fun passwordChangeValue(password: String) {
        _password.value = password
    }

    fun confirmPasswordChangeValue(confirmPassword: String) {
        _confirmPassword.value = confirmPassword
    }

}
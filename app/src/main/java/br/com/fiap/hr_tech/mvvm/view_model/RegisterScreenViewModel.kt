package br.com.fiap.hr_tech.mvvm.view_model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import br.com.fiap.hr_tech.R
import br.com.fiap.hr_tech.database.RetrofitFactory
import br.com.fiap.hr_tech.mvvm.model.User
import br.com.fiap.hr_tech.navigation.AppRoutes
import br.com.fiap.hr_tech.util.GlobalObject
import br.com.fiap.hr_tech.util.TypeMessage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    fun doRegister(
        name: String,
        email: String,
        password: String,
        confirmPassword: String,
        navController: NavController,
        context: Context
    ) {
        val user = User(0, name, email, password, 1)
        if (verifyErrors(user, confirmPassword, context)) {
            val call = RetrofitFactory().getDBService().postUser(user, "", 0)
            call.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        navController.navigate(AppRoutes.LOGIN_ROUTE)
                        GlobalObject.message.addMessage(
                            context.getString(R.string.user_register_success),
                            TypeMessage.SUCCESS
                        )
                    } else {
                        GlobalObject.message.addMessage(
                            response.toString(),
                            TypeMessage.ERROR
                        )
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    GlobalObject.message.addMessage(
                        context.getString(R.string.api_failure_response),
                        TypeMessage.ERROR
                    )
                }
            })
        }
    }

    private fun verifyErrors(user: User, confirmPassword: String, context: Context): Boolean {
        var registerOk = true
        if (user.name.isEmpty()) {
            GlobalObject.message.addMessage(
                context.getString(R.string.name_required),
                TypeMessage.ERROR
            )
            registerOk = false
        }
        if (user.email.isEmpty()) {
            GlobalObject.message.addMessage(
                context.getString(R.string.email_required),
                TypeMessage.ERROR
            )
            registerOk = false
        }
        if (user.password.isEmpty()) {
            GlobalObject.message.addMessage(
                context.getString(R.string.password_required),
                TypeMessage.ERROR
            )
            registerOk = false
        } else if (user.password !== confirmPassword) {
            GlobalObject.message.addMessage(
                context.getString(R.string.password_not_match),
                TypeMessage.ERROR
            )
        }
        return registerOk
    }

}
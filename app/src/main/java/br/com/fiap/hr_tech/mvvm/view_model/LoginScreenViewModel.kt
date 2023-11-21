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

    fun doLogin(email: String, password: String, navController: NavController, context: Context) {
        val call = RetrofitFactory().getDBService().getUserByEmail(email)
        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val users = response.body()
                if (users!!.isNotEmpty()) {
                    users.forEach { user ->
                        if (user.password == password) {
                            GlobalObject.user = user
                            navController.navigate(AppRoutes.WORK_HOURS_ROUTE)
                        } else {
                            GlobalObject.user = null
                            GlobalObject.message.addMessage(
                                context.getString(R.string.user_login_incorrect),
                                TypeMessage.ERROR
                            )
                        }

                    }
                } else {
                    GlobalObject.user = null
                    GlobalObject.message.addMessage(
                        context.getString(R.string.user_login_incorrect),
                        TypeMessage.ERROR
                    )
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                GlobalObject.message.addMessage(
                    context.getString(R.string.api_failure_response),
                    TypeMessage.ERROR
                )
                println(t.stackTraceToString())
            }

        })
    }

}
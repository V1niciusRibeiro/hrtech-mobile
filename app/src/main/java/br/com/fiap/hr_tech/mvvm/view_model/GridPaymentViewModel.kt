package br.com.fiap.hr_tech.mvvm.view_model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.fiap.hr_tech.R
import br.com.fiap.hr_tech.database.RetrofitFactory
import br.com.fiap.hr_tech.mvvm.model.Payment
import br.com.fiap.hr_tech.util.GlobalObject
import br.com.fiap.hr_tech.util.TypeMessage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GridPaymentViewModel {

    private val _openDropDownMenu = MutableLiveData<Boolean>()
    val openDropDownMenu: LiveData<Boolean> = _openDropDownMenu

    private val _openPopup = MutableLiveData<Boolean>()
    val openPopup: LiveData<Boolean> = _openPopup

    private val _selectedYear = MutableLiveData<Int>()
    val selectedYear: LiveData<Int> = _selectedYear

    private val _selectedMonths = MutableLiveData<List<Int>>()
    val selectedMonths: LiveData<List<Int>> = _selectedMonths

    private val _payments = MutableLiveData<List<Payment>>()
    val payments: LiveData<List<Payment>> = _payments

    fun openDropDownMenuChangeValue(open: Boolean) {
        _openDropDownMenu.value = open
    }

    fun openPopupChangeValue(open: Boolean) {
        _openPopup.value = open
    }

    fun selectedYearChangeValue(year: Int) {
        _selectedYear.value = year
    }

    fun selectedMonthsPlus(month: Int, selectedMonths: List<Int>) {
        _selectedMonths.value = (selectedMonths.toMutableList() + month).toList()
    }

    fun selectedMonthsMinus(month: Int, selectedMonths: List<Int>) {
        _selectedMonths.value = (selectedMonths.toMutableList() - month).toList()
    }

    fun selectAllMonths() {
        _selectedMonths.value = (1..12).toList()
    }

    fun clearSelectedMonths() {
        _selectedMonths.value = listOf()
    }

    fun getPayments(selectedMonths: List<Int>, year: Int, context: Context) {
        var payments = listOf<Payment>()
        _payments.value = listOf()
        selectedMonths.forEach {
            val call =
                RetrofitFactory().getDBService().getUserPayments(it, year, GlobalObject.user!!.id)
            call.enqueue(object : Callback<Payment> {
                override fun onResponse(call: Call<Payment>, response: Response<Payment>) {
                    if (response.body() != null) {
                        payments = (payments.toMutableList() + response.body()!!).toList()
                        _payments.value = payments.toList()
                    }
                }

                override fun onFailure(call: Call<Payment>, t: Throwable) {
                    GlobalObject.message.addMessage(
                        context.getString(R.string.api_failure_response),
                        TypeMessage.ERROR
                    )
                    println(t.stackTraceToString())
                }
            })
            openPopupChangeValue(true)
        }
    }

}
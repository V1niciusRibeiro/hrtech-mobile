package br.com.fiap.hr_tech.mvvm.view_model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.fiap.hr_tech.R
import br.com.fiap.hr_tech.database.RetrofitFactory
import br.com.fiap.hr_tech.mvvm.model.WorkHour
import br.com.fiap.hr_tech.util.GlobalObject
import br.com.fiap.hr_tech.util.TypeMessage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class WorkHoursScreenViewModel {

    private val _workHours = MutableLiveData<List<WorkHour>>()
    val workHours: LiveData<List<WorkHour>> = _workHours

    private val _workHour = MutableLiveData<WorkHour>()
    val workHour: LiveData<WorkHour> = _workHour

    private val _retractCalendar = MutableLiveData<Boolean>()
    val retractCalendar: LiveData<Boolean> = _retractCalendar

    private val _openPopup = MutableLiveData<Boolean>()
    val openPopup: LiveData<Boolean> = _openPopup

    private val _dateSelected = MutableLiveData<LocalDate?>()
    val dateSelected: LiveData<LocalDate?> = _dateSelected

    private val _descriptionPopup = MutableLiveData<String>()
    val descriptionPopup: LiveData<String> = _descriptionPopup

    private val _hourPopup = MutableLiveData<String>()
    val hourPopup: LiveData<String> = _hourPopup

    fun retractCalendarChangeValue(retract: Boolean) {
        _retractCalendar.value = retract
    }

    fun openPopupChangeValue(openPopup: Boolean) {
        _openPopup.value = openPopup
    }

    fun dateSelectedChangeValue(localDate: LocalDate?, context: Context) {
        _dateSelected.value = localDate
        val call = RetrofitFactory().getDBService()
            .getUserWorkHours(localDate.toString(), GlobalObject.user!!.id)
        call.enqueue(object : Callback<List<WorkHour>> {
            override fun onResponse(
                call: Call<List<WorkHour>>,
                response: Response<List<WorkHour>>
            ) {
                println(call.request().url())
                println(response.toString())
                _workHours.value = if (response.body() != null) response.body() else listOf()
            }

            override fun onFailure(call: Call<List<WorkHour>>, t: Throwable) {
                GlobalObject.message.addMessage(
                    context.getString(R.string.api_failure_response),
                    TypeMessage.ERROR
                )
                println(t.stackTraceToString())
            }

        })
    }

    fun descriptionPopupChangeValue(description: String) {
        _descriptionPopup.value = description
    }

    fun hourPopupChangeValue(hour: String) {
        var index = 0
        var hourOut = hour
        hour.forEach {
            if ((index == 2) and (it != ':')) {
                hourOut = hour.substring(0, index) + ":" + hour.substring(index, hour.length)
            }
            index += 1
        }
        _hourPopup.value = hourOut.substring(0, if (hourOut.length > 5) 5 else hourOut.length)
    }

    fun newPopupRegister(dateSelected: LocalDate) {
        _workHour.value = WorkHour(
            0,
            "",
            LocalDateTime.of(dateSelected, LocalTime.MIDNIGHT).toString(),
            GlobalObject.user!!
        )
        _descriptionPopup.value = ""
        _hourPopup.value = ""
        _openPopup.value = true
    }

    fun selectPopupRegister(workHour: WorkHour) {
        val localDateTime =
            LocalDateTime.parse(workHour.date, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        _workHour.value = workHour
        _descriptionPopup.value = workHour.description
        _hourPopup.value = "${localDateTime.hour}:${localDateTime.minute}"
        _openPopup.value = true
    }

    fun saveWorkHour(description: String, hour: String, workHour: WorkHour, context: Context) {
        if (verifyErrors(hour, context)) {
            workHour.description = description
            workHour.date = changeHour(workHour.date, hour)
            if (workHour.id > 0) {
                updateWorkHour(workHour, context)
            } else {
                insertWorkHour(workHour, context)
            }
        }
        openPopupChangeValue(false)
    }

    private fun changeHour(date: String, hour: String): String {
        var count = 1
        var localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        hour.split(":").forEach {
            if (count == 1) {
                localDateTime = localDateTime.withHour(it.toInt())
            } else {
                localDateTime = localDateTime.withMinute(it.toInt())
            }
            count += 1
        }
        localDateTime = localDateTime.withSecond(0)
        localDateTime = localDateTime.withNano(0)
        return localDateTime.toString()
    }

    private fun insertWorkHour(workHour: WorkHour, context: Context) {
        val call = RetrofitFactory().getDBService().postWorkHour(workHour)
        call.enqueue(object : Callback<WorkHour> {
            override fun onResponse(call: Call<WorkHour>, response: Response<WorkHour>) {
                GlobalObject.message.addMessage(
                    context.getString(R.string.work_hour_register_success),
                    TypeMessage.SUCCESS
                )
            }

            override fun onFailure(call: Call<WorkHour>, t: Throwable) {
                GlobalObject.message.addMessage(
                    context.getString(R.string.api_failure_response),
                    TypeMessage.ERROR
                )
                println(t.stackTraceToString())
            }
        })
    }

    private fun updateWorkHour(workHour: WorkHour, context: Context) {
        val call = RetrofitFactory().getDBService().putWorkHour(workHour.id, workHour)
        call.enqueue(object : Callback<WorkHour> {
            override fun onResponse(call: Call<WorkHour>, response: Response<WorkHour>) {
                GlobalObject.message.addMessage(
                    context.getString(R.string.work_hour_update_success),
                    TypeMessage.SUCCESS
                )
            }

            override fun onFailure(call: Call<WorkHour>, t: Throwable) {
                GlobalObject.message.addMessage(
                    context.getString(R.string.api_failure_response),
                    TypeMessage.ERROR
                )
                println(t.stackTraceToString())
            }
        })
    }

    fun deleteWorkHour(workHour: WorkHour, context: Context) {
        val call = RetrofitFactory().getDBService().deleteWorkHour(workHour.id)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                GlobalObject.message.addMessage(
                    context.getString(R.string.work_hour_delete_success),
                    TypeMessage.SUCCESS
                )
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                GlobalObject.message.addMessage(
                    context.getString(R.string.api_failure_response),
                    TypeMessage.ERROR
                )
                println(t.stackTraceToString())
            }
        })
        openPopupChangeValue(false)
    }

    private fun verifyErrors(hour: String, context: Context): Boolean {
        var workHourOk = true
        if (hour.length < 5) {
            GlobalObject.message.addMessage(
                context.getString(R.string.hour_incorrect),
                TypeMessage.ERROR
            )
            workHourOk = false
        } else if ((hour.substring(0, 2).toInt() > 24) or (hour.substring(0, 2).toInt() < 1) or
            (hour.substring(3, 5).toInt() > 59) or (hour.substring(3, 5).toInt() < 1)
        ) {
            GlobalObject.message.addMessage(
                context.getString(R.string.hour_incorrect),
                TypeMessage.ERROR
            )
            workHourOk = false
        }
        return workHourOk
    }

}
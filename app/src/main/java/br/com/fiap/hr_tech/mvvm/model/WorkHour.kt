package br.com.fiap.hr_tech.mvvm.model

import com.google.gson.annotations.SerializedName

data class WorkHour(

    @SerializedName("id")
    val id: Long,

    @SerializedName("pontp")
    var description: String,

    @SerializedName("data")
    var date: String,

    @SerializedName("usuario")
    val user: User

)

package br.com.fiap.hr_tech.mvvm.model

import com.google.gson.annotations.SerializedName

data class Payment(

    @SerializedName("id")
    val id: Long,

    @SerializedName("data")
    val date: String,

    @SerializedName("valor")
    val value: Float,

    @SerializedName("usuario")
    val user: User,

    @SerializedName("reajustes")
    val adjustments: List<Adjustment>

)

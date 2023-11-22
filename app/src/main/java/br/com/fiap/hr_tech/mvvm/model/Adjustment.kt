package br.com.fiap.hr_tech.mvvm.model

import com.google.gson.annotations.SerializedName

data class Adjustment(

    @SerializedName("id")
    val id: Long,

    @SerializedName("nome")
    val name: String,

    @SerializedName("valor")
    val value: Float,

    @SerializedName("holerite")
    val payment: Payment

)

package br.com.fiap.hr_tech.mvvm.model

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("id")
    val id: Int,

    @SerializedName("nome")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("senha")
    val password: String,

    @SerializedName("id_cargo")
    val position: Int

)
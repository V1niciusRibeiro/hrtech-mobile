package br.com.fiap.hr_tech.database

import br.com.fiap.hr_tech.mvvm.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DBService {

    @GET("usuario/pelo-email")
    fun getUserByEmail(@Query("email") email: String): Call<List<User>>

    @GET("/usuario/lista")
    fun getUsers(): Call<User>

    @POST("/usuario/postar")
    fun postUser(
        @Body user: User,
        @Query("nomeCargo") nomeCargo: String?,
        @Query("nivel") nivel: Int?
    ): Call<User>

}
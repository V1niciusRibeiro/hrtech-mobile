package br.com.fiap.hr_tech.database

import br.com.fiap.hr_tech.mvvm.model.Payment
import br.com.fiap.hr_tech.mvvm.model.User
import br.com.fiap.hr_tech.mvvm.model.WorkHour
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface DBService {

    @GET("usuario/pelo-email")
    fun getUserByEmail(@Query("email") email: String): Call<User>

    @POST("/usuario/postar")
    fun postUser(
        @Body user: User,
        @Query("nomeCargo") nomeCargo: String?,
        @Query("nivel") nivel: Int?
    ): Call<User>


    @GET("/pontos/pelo-dia-usuario")
    fun getUserWorkHours(
        @Query("data") date: String?,
        @Query("usuarioId") userId: Int?
    ): Call<List<WorkHour>>

    @POST("/pontos/postar")
    fun postWorkHour(@Body workHour: WorkHour): Call<WorkHour>

    @PUT("/pontos/editar/{id}")
    fun putWorkHour(@Path("id") id: Long, @Body workHour: WorkHour): Call<WorkHour>

    @DELETE("/pontos/deletar/{id}")
    fun deleteWorkHour(@Path("id") id: Long): Call<Void>


    @GET("/holerite/pelo-mes-ano-usuario")
    fun getUserPayments(
        @Query("mes") mes: Int?,
        @Query("ano") ano: Int?,
        @Query("usuarioId") userId: Int?
    ): Call<Payment>


}
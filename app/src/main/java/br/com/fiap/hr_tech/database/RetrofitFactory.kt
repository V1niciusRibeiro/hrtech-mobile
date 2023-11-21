package br.com.fiap.hr_tech.database

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val URL = "https://hrtech-api-production.up.railway.app"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getDBService(): DBService {
        return retrofitFactory.create(DBService::class.java)
    }

}
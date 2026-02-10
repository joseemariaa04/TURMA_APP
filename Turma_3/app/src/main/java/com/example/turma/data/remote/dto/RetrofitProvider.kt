package com.example.turma.data.remote.dto

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.getValue

object RetrofitProvider {
    private const val URL_BASE="https://69721e3132c6bacb12c5d94b.mockapi.io/"
    val api: TurmaAPI by lazy {
        Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TurmaAPI::class.java)
    }
}
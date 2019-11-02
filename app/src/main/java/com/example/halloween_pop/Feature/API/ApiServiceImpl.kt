package com.example.halloween_pop.Feature.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiServiceImpl {
    private const val BASE_URL = "https://3.215.131.244:3000"

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val SERVICE : ApiService = retrofit.create(ApiService::class.java)
}
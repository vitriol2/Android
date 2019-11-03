package com.example.halloween_pop.Feature.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SopkatonServiceImpl {
    private const val BASE_URL = "http://3.215.131.244:3000"

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service : SopkatonService = retrofit.create(
        SopkatonService::class.java)
}
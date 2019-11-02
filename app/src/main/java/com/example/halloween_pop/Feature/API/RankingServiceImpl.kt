package com.example.halloween_pop.Feature.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RankingServiceImpl {
    private const val BASE_URL = "https://69ffb87f.ngrok.io"

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service : RankingService = retrofit.create(RankingService::class.java)
}
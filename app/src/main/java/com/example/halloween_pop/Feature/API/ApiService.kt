package com.example.halloween_pop.Feature.API

import com.example.halloween_pop.Data.Game.StageData
import com.example.halloween_pop.Data.Ranking.RankingData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService{
    @GET("/api/result/{rank}")
    fun getRank(
        @Path("rank") rank : String
    ) : Call<List<RankingData>>

    @GET("/api/img")
    fun getImage(
    ) : Call<StageData>

}
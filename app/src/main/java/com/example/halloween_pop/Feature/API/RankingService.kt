package com.example.halloween_pop.Feature.API

import com.example.halloween_pop.Data.Ranking.RankingData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RankingService{
    @GET("/api/result/{rank}")
    fun getRank(
        @Path("rank") rank : String
    ) : Call<List<RankingData>>

}
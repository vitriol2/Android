package com.example.halloween_pop.Feature.API

import com.example.halloween_pop.Data.Game.Stage4PictureData
import com.example.halloween_pop.Data.Game.StageData
import com.example.halloween_pop.Data.Ranking.RankingData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService{
    @GET("/api/result/{rank}")
    fun getRank(
        @Path("rank") rank : String
    ) : Call<List<RankingData>>

    @GET("/api/imgs/{stage}")
    fun getstagePicture(
        @Path("stage") stage : String
    ) : Call<Stage4PictureData>


}
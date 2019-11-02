package com.example.halloween_pop.Data.Ranking

import com.google.gson.annotations.SerializedName

data class RankingData(
    @SerializedName("id")
    val id : String,
    @SerializedName("total")
    val time : Int
)
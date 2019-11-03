package com.example.halloween_pop.Feature.Ranking

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.halloween_pop.Data.Ranking.RankingData
import com.example.halloween_pop.R

class RankingAdapter( private val context : Context) : RecyclerView.Adapter<RankingViewHolder>() {
    var data : List<RankingData> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rank_item, parent, false)

        return RankingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        holder.setting(data[position])
    }
}
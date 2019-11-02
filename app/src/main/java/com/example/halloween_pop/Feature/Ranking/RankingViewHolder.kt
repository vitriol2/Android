package com.example.halloween_pop.Feature.Ranking

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.halloween_pop.Data.Ranking.RankingData
import com.example.halloween_pop.R

class RankingViewHolder(view : View) : RecyclerView.ViewHolder(view){
    val id : TextView = view.findViewById(R.id.R_id)
    val time : TextView = view.findViewById(R.id.R_time)

   fun setting(data : RankingData){
        id.text = data.id
        time.text = data.time.toString()
    }

}
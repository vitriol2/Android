package com.example.halloween_pop.Feature.Ranking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.halloween_pop.R

class RankingActivity : AppCompatActivity() {
    private lateinit var adapter : RankingAdapter
    private lateinit var mainRcv : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        initRanking()
    }

    private fun initRanking(){
        mainRcv = findViewById(R.id.Rcv)
        adapter = RankingAdapter(this)
        //리사이클러뷰에 어댑터 장착
        mainRcv.adapter = adapter
        mainRcv.layoutManager = LinearLayoutManager(this)

        // 서버 통신해서 데이터 받아 어댑터에 장착해주는 작업 필요
    }
}

package com.example.halloween_pop.Feature.Ranking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.halloween_pop.Data.Ranking.RankingData
import com.example.halloween_pop.Feature.API.RankingServiceImpl
import com.example.halloween_pop.R
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        var title : TextView = findViewById(R.id.rank_title)
        var id : TextView = findViewById(R.id.nickname)
        var score : TextView = findViewById(R.id.score)
//        var back : ImageView = findViewById(R.id.background)

        // 서버 통신해서 데이터 받아 어댑터에 장착해주는 작업 필요
        val call : Call<List<RankingData>> = RankingServiceImpl.service.getRank("ranks")
        call.enqueue(
            object : Callback<List<RankingData>>{
                override fun onFailure(call: Call<List<RankingData>>, t: Throwable) {

                }
                override fun onResponse(
                    call: Call<List<RankingData>>,
                    response: Response<List<RankingData>>
                ) {
                    Log.e("suc","success")
                    if(response.isSuccessful){

                        val repos = response.body()!!

                        adapter.data = repos

                        adapter.notifyDataSetChanged()
                    }
                }
            }
        )


    }
}

package com.example.halloween_pop.Feature.Menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.halloween_pop.Feature.Ranking.RankingActivity
import com.example.halloween_pop.R

class MenuActivity : AppCompatActivity() {
    private lateinit var start :TextView
    private lateinit var rank : TextView
    private lateinit var config : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        start = findViewById(R.id.start)
        rank = findViewById(R.id.rank)
        config = findViewById(R.id.config)

        start.setOnClickListener {

        }

        rank.setOnClickListener {

            var intent = Intent(this, RankingActivity::class.java)
            startActivity(intent)
        }

        config.setOnClickListener {


        }
    }
}

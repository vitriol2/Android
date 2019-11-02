package com.example.halloween_pop.Feature.Game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.halloween_pop.R

class StageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stage)

        val image1 : ImageView = findViewById(R.id.Stage1)
        val image2 : ImageView = findViewById(R.id.Stage2)
        val image3 : ImageView = findViewById(R.id.Stage3)
        val image4 : ImageView = findViewById(R.id.Stage4)
        val image5 : ImageView = findViewById(R.id.Stage5)
        val image6 : ImageView = findViewById(R.id.Stage6)
        val image7 : ImageView = findViewById(R.id.Stage7)
        val image8 : ImageView = findViewById(R.id.Stage8)
        val image9 : ImageView = findViewById(R.id.Stage9)


        image1.setOnClickListener{
            intent = Intent(this,PlayActivity::class.java)
            startActivity(intent)

        }
        image2.setOnClickListener{
            intent = Intent(this,PlayActivity::class.java)
            startActivity(intent)

        }
        image3.setOnClickListener{
            intent = Intent(this,PlayActivity::class.java)
            startActivity(intent)

        }
        image4.setOnClickListener{
            intent = Intent(this,PlayActivity::class.java)
            startActivity(intent)

        }
        image5.setOnClickListener{
            intent = Intent(this,PlayActivity::class.java)
            startActivity(intent)

        }
        image6.setOnClickListener{
            intent = Intent(this,PlayActivity::class.java)
            startActivity(intent)

        }
        image7.setOnClickListener{
            intent = Intent(this,PlayActivity::class.java)
            startActivity(intent)

        }
        image8.setOnClickListener{
            intent = Intent(this,PlayActivity::class.java)
            startActivity(intent)

        }
        image9.setOnClickListener{
            intent = Intent(this,PlayActivity::class.java)
            startActivity(intent)

        }
    }
}
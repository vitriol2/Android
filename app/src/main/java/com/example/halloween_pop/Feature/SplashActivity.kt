package com.example.halloween_pop.Feature

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.halloween_pop.Feature.SignIn.MainActivity
import com.example.halloween_pop.R

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME : Long = 3000 //3초

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME)
    }
}
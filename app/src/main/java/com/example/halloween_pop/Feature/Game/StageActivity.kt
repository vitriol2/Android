package com.example.halloween_pop.Feature.Game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.halloween_pop.Data.Game.Stage4PictureData
import com.example.halloween_pop.Feature.API.RankingServiceImpl
import com.example.halloween_pop.R
import kotlinx.android.synthetic.main.activity_stage.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.Collections.shuffle
import kotlin.concurrent.timer

class StageActivity : AppCompatActivity() {

    private var time = 0
    private var isRunning = false
    private var timerTask: Timer? = null
    private var lap = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stage)

        var list = listOf("http://ghkdua1829.dothome.co.kr/id/20190728151519.png","2","http://ghkdua1829.dothome.co.kr/id/20190728151519.png","4",
            "http://ghkdua1829.dothome.co.kr/id/20190728151519.png","6","http://ghkdua1829.dothome.co.kr/id/20190728151519.png","8")

        val call: Call<Stage4PictureData> = RankingServiceImpl.service.getstagePicture("4")

        call.enqueue(
            object :Callback<Stage4PictureData>{
                override fun onFailure(call: Call<Stage4PictureData>, t: Throwable) {
                    Log.e("err",t.toString())
                }

                override fun onResponse(
                    call: Call<Stage4PictureData>,
                    response: Response<Stage4PictureData>
                ) {
                    if(response.isSuccessful){
                        val gitStage=response.body()!!
                        list=gitStage.lst
                        Log.e("list? ",""+list)
                        Log.e("sss",""+gitStage)
                    }
                }
            }
        )
        start()

        one.setTag(list[0])
        two.setTag(list[1])
        three.setTag(list[2])
        four.setTag(list[3])
        five.setTag(list[4])
        six.setTag(list[5])
        seven.setTag(list[6])
        eight.setTag(list[7])
        nine.setTag("기준")

        Glide.with(this).load(one.getTag().toString()).into(one)
        Glide.with(this).load(two.getTag().toString()).into(two)
        Glide.with(this).load(three.getTag().toString()).into(three)
        Glide.with(this).load(four.getTag().toString()).into(four)
        Glide.with(this).load(five.getTag().toString()).into(five)
        Glide.with(this).load(six.getTag().toString()).into(six)
        Glide.with(this).load(seven.getTag().toString()).into(seven)
        Glide.with(this).load(eight.getTag().toString()).into(eight)
        Glide.with(this).load(nine.getTag().toString()).into(nine)

            Shuffle(list)

        one.setOnClickListener {
            if(four.getTag().equals("기준")){
                change(one,four)
            }
            if(two.getTag().equals("기준")){
                change(one,two)
            }
            if(success(list)){
                Toast.makeText(this,"성공!",Toast.LENGTH_SHORT).show()
            }
        }
        two.setOnClickListener {
            if(one.getTag().equals("기준")){
                change(two,one)
            }
            if(three.getTag().equals("기준")){
                change(two,three)
            }
            if(five.getTag().equals("기준")){
                change(two,five)
            }
            if(success(list)){
                Toast.makeText(this,"성공!",Toast.LENGTH_SHORT).show()
            }
        }
        three.setOnClickListener {
            if(two.getTag().equals("기준")){
                change(three,two)
            }
            if(six.getTag().equals("기준")){
                change(three,six)
            }
            if(success(list)){
                Toast.makeText(this,"성공!",Toast.LENGTH_SHORT).show()
            }
        }
        four.setOnClickListener {
            if(one.getTag().equals("기준")){
                change(four,one)
            }
            if(five.getTag().equals("기준")){
                change(four,five)
            }
            if(seven.getTag().equals("기준")){
                change(four,seven)
            }
            if(success(list)){
                Toast.makeText(this,"성공!",Toast.LENGTH_SHORT).show()
            }
        }
        five.setOnClickListener {
            if(two.getTag().equals("기준")){
                change(five,two)
            }
            if(four.getTag().equals("기준")){
                change(five,four)
            }
            if(six.getTag().equals("기준")){
                change(five,six)
            }
            if(eight.getTag().equals("기준")){
                change(five,eight)
            }
            if(success(list)){
                Toast.makeText(this,"성공!",Toast.LENGTH_SHORT).show()
            }
        }
        six.setOnClickListener {
            if(three.getTag().equals("기준")){
                change(six,three)
            }
            if(five.getTag().equals("기준")){
                change(six,five)
            }
            if(nine.getTag().equals("기준")){
                change(six,nine)
            }
            if(success(list)){
                Toast.makeText(this,"성공!",Toast.LENGTH_SHORT).show()
            }
        }
        seven.setOnClickListener {
            if(four.getTag().equals("기준")){
                change(seven,four)
            }
            if(eight.getTag().equals("기준")){
                change(seven,eight)
            }
            if(success(list)){
                Toast.makeText(this,"성공!",Toast.LENGTH_SHORT).show()
            }
        }
        eight.setOnClickListener {
            if(five.getTag().equals("기준")){
                change(eight,five)
            }
            if(seven.getTag().equals("기준")){
                change(eight,seven)
            }
            if(nine.getTag().equals("기준")){
                change(eight,nine)
            }
            if(success(list)){
                Toast.makeText(this,"성공!",Toast.LENGTH_SHORT).show()
            }
        }
        nine.setOnClickListener {
            if(six.getTag().equals("기준")){
                change(nine,six)
            }
            if(eight.getTag().equals("기준")){
                change(nine,eight)
            }
            if(success(list)){
                Toast.makeText(this,"성공!",Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun change(a:ImageView,b:ImageView){
        var change = a.getTag().toString()
        Glide.with(this).load(a.getTag().toString()).into(b)
        Glide.with(this).load(b.getTag().toString()).into(a)
        a.setTag(b.getTag().toString())
        b.setTag(change)

    }
    fun Shuffle(a:List<String>){

         shuffle(a)
        one.setTag(a[0])
        two.setTag(a[1])
        three.setTag(a[2])
        four.setTag(a[3])
        five.setTag(a[4])
        six.setTag(a[5])
        seven.setTag(a[6])
        eight.setTag(a[7])

        Glide.with(this).load(one.getTag().toString()).into(one)
        Glide.with(this).load(two.getTag().toString()).into(two)
        Glide.with(this).load(three.getTag().toString()).into(three)
        Glide.with(this).load(four.getTag().toString()).into(four)
        Glide.with(this).load(five.getTag().toString()).into(five)
        Glide.with(this).load(six.getTag().toString()).into(six)
        Glide.with(this).load(seven.getTag().toString()).into(seven)
        Glide.with(this).load(eight.getTag().toString()).into(eight)
    }
    fun success(a:List<String>):Boolean{
        if(a[0]==one.getTag().toString() && a[1]==two.getTag().toString() && a[2]==three.getTag().toString() &&
            a[3]==four.getTag().toString() && a[4]==five.getTag().toString() && a[5]==six.getTag().toString() &&
            a[6]==seven.getTag().toString() && a[7]==eight.getTag().toString()){
            return true
        }
        else
            return false
    }

    private fun start() { //타이머 스타트

        timerTask = timer(period = 10) {
            // period = 10 0.01초 , period = 1000 면 1초
            time++
            // 0.01초마다 변수를 증가시킴

            val hour = (time / 144000) % 24 // 1시간
            val min = (time / 6000) % 60 // 1분
            val sec = (time / 100) % 60 //1초
            val milli = time % 100 // 0.01 초
            runOnUiThread {
                // Ui 를 갱신 시킴.

                if (min < 10) { // 분
                    minTextView.text = "0$min"
                } else {
                    minTextView.text = "$min"
                }

                if (sec < 10) { // 초
                    secTextView.text = "0$sec"
                } else {
                    secTextView.text = "$sec"
                }

                if (milli < 10){
                    milliTextView.text = "0$milli"
                }else {
                    milliTextView.text = "$milli"
                }

                //$ 를 붙여주면 변하는 값을 계속 덮어준다
                //ex) $를 붙여주면 기존에 1이라는 값이 잇을때 값이 2로변하면 2로 바꿔준다.

            }
        }
    }


}

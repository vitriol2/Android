package com.example.halloween_pop.Feature.Game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.Display
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.view.marginBottom
import com.bumptech.glide.Glide
import com.example.halloween_pop.Data.Game.Stage4PictureData
import com.example.halloween_pop.Feature.API.ApiServiceImpl
import com.example.halloween_pop.R
import kotlinx.android.synthetic.main.activity_play.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class PlayActivity : AppCompatActivity() {

    private val TAG = "PlayActivity"

    private var time = 0
    private var isRunning = false
    private var timerTask: Timer? = null
    private var lap = 1
    private var levelNum: Int = 1
    var timeMax: Long = 0L



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        setScreenSize()

        levelNum = intent.getIntExtra("num", 1)
        tv_level.text = levelNum.toString()
        setProgressBarTime(levelNum)

//
//        var list = listOf(
//            "http://ghkdua1829.dothome.co.kr/id/20190728151519.png",
//            "2",
//            "http://ghkdua1829.dothome.co.kr/id/20190728151519.png",
//            "4",
//            "http://ghkdua1829.dothome.co.kr/id/20190728151519.png",
//            "6",
//            "http://ghkdua1829.dothome.co.kr/id/20190728151519.png",
//            "8"
//        )

        setGame()

    }
    private fun dpToPixel(dp : Float) : Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp, this.resources.displayMetrics).toInt()
    }
    private fun dpToPixel2(dp : Float) : Int {
        val displayMetrics : DisplayMetrics = this.resources.displayMetrics
        val px = (dp * (displayMetrics.densityDpi)/160.0f).toInt()
        return px
    }

    private fun setScreenSize() {
        val display: Display = windowManager.defaultDisplay
        val fullWidth = display.width
        val frameWidth : Int = fullWidth - dpToPixel(32f)
        val frameHeight = frameWidth + dpToPixel(40f)
        val gameWidth = frameWidth - dpToPixel(64f)
        val pieceWidth = gameWidth.div(3)

        //액자 사이즈
        rl_game_screen.let {
            it.layoutParams = LinearLayout.LayoutParams(frameWidth, frameHeight)
        }

//        val params = RelativeLayout.LayoutParams(gameWidth, gameWidth)
////        params.setMargins(0, 0, 0, dpToPixel(32f))
//
//        gl_game.setLayoutParams(params)


//        조각사이즈
        one.layoutParams.run{
            LinearLayout.LayoutParams(pieceWidth, pieceWidth)
        }
        two.layoutParams.run{
            width = pieceWidth
            height = pieceWidth
        }
        three.layoutParams.run{
            width = pieceWidth
            height = pieceWidth
        }
        four.layoutParams.run{
            width = pieceWidth
            height = pieceWidth
        }
        five.layoutParams.run{
            width = pieceWidth
            height = pieceWidth
        }
        six.layoutParams.run{
            width = pieceWidth
            height = pieceWidth
        }
        seven.layoutParams.run{
            width = pieceWidth
            height = pieceWidth
        }
        eight.layoutParams.run{
            width = pieceWidth
            height = pieceWidth
        }
        nine.layoutParams.run{
            width = pieceWidth
            height = pieceWidth
        }
    }


    private fun setGame() {
        var list = listOf<String>()
        var number: String = "1"
        Log.d("num", number)
        val call: Call<Stage4PictureData> = ApiServiceImpl.SERVICE.getstagePicture(number)

        call.enqueue(
            object : Callback<Stage4PictureData> {
                override fun onFailure(call: Call<Stage4PictureData>, t: Throwable) {
                    Log.e("err", t.toString())
                }

                override fun onResponse(
                    call: Call<Stage4PictureData>,
                    response: Response<Stage4PictureData>
                ) {
                    if (response.isSuccessful) {
                        val gitStage = response.body()!!
                        list = gitStage.lst
                        one.setTag(list[0])
                        two.setTag(list[1])
                        three.setTag(list[2])
                        four.setTag(list[3])
                        five.setTag(list[4])
                        six.setTag(list[5])
                        seven.setTag(list[6])
                        eight.setTag(list[7])
                        nine.setTag("기준")

                        Glide.with(this@PlayActivity).load(one.getTag().toString()).into(one)
                        Glide.with(this@PlayActivity).load(two.getTag().toString()).into(two)
                        Glide.with(this@PlayActivity).load(three.getTag().toString()).into(three)
                        Glide.with(this@PlayActivity).load(four.getTag().toString()).into(four)
                        Glide.with(this@PlayActivity).load(five.getTag().toString()).into(five)
                        Glide.with(this@PlayActivity).load(six.getTag().toString()).into(six)
                        Glide.with(this@PlayActivity).load(seven.getTag().toString()).into(seven)
                        Glide.with(this@PlayActivity).load(eight.getTag().toString()).into(eight)
                        Glide.with(this@PlayActivity).load(nine.getTag().toString()).into(nine)

                        shuffle(list)

                        Log.e("list? ", "" + list)
                        Log.e("sss", "" + gitStage)
                    }
                }
            }
        )

        one.setOnClickListener {
            if (four.getTag().equals("기준")) {
                change(one, four)
            }
            if (two.getTag().equals("기준")) {
                change(one, two)
            }
            if (success(list)) {
                win_img.bringToFront()
                win_img.visibility = View.VISIBLE
                val intent = Intent(this@PlayActivity, StageActivity::class.java)
                startActivity(intent)
            }
        }
        two.setOnClickListener {
            if (one.getTag().equals("기준")) {
                change(two, one)
            }
            if (three.getTag().equals("기준")) {
                change(two, three)
            }
            if (five.getTag().equals("기준")) {
                change(two, five)
            }
            if (success(list)) {
                win_img.bringToFront()
                win_img.visibility = View.VISIBLE
                val intent = Intent(this@PlayActivity, StageActivity::class.java)
                startActivity(intent)
            }
        }
        three.setOnClickListener {
            if (two.getTag().equals("기준")) {
                change(three, two)
            }
            if (six.getTag().equals("기준")) {
                change(three, six)
            }
            if (success(list)) {
                win_img.bringToFront()
                win_img.visibility = View.VISIBLE
                val intent = Intent(this@PlayActivity, StageActivity::class.java)
                startActivity(intent)
            }
        }
        four.setOnClickListener {
            if (one.getTag().equals("기준")) {
                change(four, one)
            }
            if (five.getTag().equals("기준")) {
                change(four, five)
            }
            if (seven.getTag().equals("기준")) {
                change(four, seven)
            }
            if (success(list)) {
                win_img.bringToFront()
                win_img.visibility = View.VISIBLE
                val intent = Intent(this@PlayActivity, StageActivity::class.java)
                startActivity(intent)
            }
        }
        five.setOnClickListener {
            if (two.getTag().equals("기준")) {
                change(five, two)
            }
            if (four.getTag().equals("기준")) {
                change(five, four)
            }
            if (six.getTag().equals("기준")) {
                change(five, six)
            }
            if (eight.getTag().equals("기준")) {
                change(five, eight)
            }
            if (success(list)) {
                win_img.bringToFront()
                win_img.visibility = View.VISIBLE
                val intent = Intent(this@PlayActivity, StageActivity::class.java)
                startActivity(intent)
            }
        }
        six.setOnClickListener {
            if (three.getTag().equals("기준")) {
                change(six, three)
            }
            if (five.getTag().equals("기준")) {
                change(six, five)
            }
            if (nine.getTag().equals("기준")) {
                change(six, nine)
            }
            if (success(list)) {
                win_img.bringToFront()
                win_img.visibility = View.VISIBLE
                val intent = Intent(this@PlayActivity, StageActivity::class.java)
                startActivity(intent)
            }
        }
        seven.setOnClickListener {
            if (four.getTag().equals("기준")) {
                change(seven, four)
            }
            if (eight.getTag().equals("기준")) {
                change(seven, eight)
            }
            if (success(list)) {
                win_img.bringToFront()
                win_img.visibility = View.VISIBLE
                val intent = Intent(this@PlayActivity, StageActivity::class.java)
                startActivity(intent)
            }
        }
        eight.setOnClickListener {
            if (five.getTag().equals("기준")) {
                change(eight, five)
            }
            if (seven.getTag().equals("기준")) {
                change(eight, seven)
            }
            if (nine.getTag().equals("기준")) {
                change(eight, nine)
            }
            if (success(list)) {
                win_img.bringToFront()
                win_img.visibility = View.VISIBLE
                val intent = Intent(this@PlayActivity, StageActivity::class.java)
                startActivity(intent)
            }
        }
        nine.setOnClickListener {
            if (six.getTag().equals("기준")) {
                change(nine, six)
            }
            if (eight.getTag().equals("기준")) {
                change(nine, eight)
            }
            if (success(list)) {
                win_img.bringToFront()
                win_img.visibility = View.VISIBLE
                val intent = Intent(this@PlayActivity, StageActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setProgressBarTime(level: Int) {
        timeMax = when (level) {
            1 -> 75000
            2 -> 60000
            3 -> 45000
            else -> 30000
        }

        pb_play2.max = (timeMax / 1000).toInt()
        startTimer(timeMax)
    }

    private fun startTimer(maxTime: Long) { //타이머 스타트
        val timer = object : CountDownTimer(maxTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val timeRemain: Int = millisUntilFinished.toInt()

                val min = (timeRemain / 60000) % 60 // 1분
                val sec = (timeRemain / 1000) % 60 //1초
//                val milli = timeRemain % 100 // 0.01 초
                runOnUiThread {
                    // Ui 를 갱신 시킴.

                    pb_play2.progress = timeRemain / 1000

                    if (min < 10) { // 분
                        minTextView.text = "0${min}"
                    } else {
                        minTextView.text = "${min}"
                    }

                    if (sec < 10) { // 초
                        secTextView.text = "0${sec}"
                    } else {
                        secTextView.text = "${sec}"
                    }
                }
            }

            override fun onFinish() {}
        }
        timer.start()
//
//        timerTask = timer(period = 10) {
//            // period = 10 0.01초 , period = 1000 면 1초
//            time++
//            // 0.01초마다 변수를 증가시킴
//            val timeRemain = 12000-time
//
//            val hour = (timeRemain / 144000) % 24 // 1시간
//            val min = (timeRemain / 6000) % 60 // 1분
//            val sec = (timeRemain / 100) % 60 //1초
//            val milli = timeRemain % 100 // 0.01 초
//            runOnUiThread {
//                // Ui 를 갱신 시킴.
//
//                if (min < 10) { // 분
//                    minTextView.text = "0${60-min}"
//                } else {
//                    minTextView.text = "${60-min}"
//                }
//
//                if (sec < 10) { // 초
//                    secTextView.text = "0${60-sec}"
//                } else {
//                    secTextView.text = "${60-sec}"
//                }
//
//                if (milli < 10){
//                    milliTextView.text = "0${60-milli}"
//                }else {
//                    milliTextView.text = "${60-milli}"
//                }
//
//                //$ 를 붙여주면 변하는 값을 계속 덮어준다
//                //ex) $를 붙여주면 기존에 1이라는 값이 잇을때 값이 2로변하면 2로 바꿔준다.
//
//            }
    }

    fun change(a: ImageView, b: ImageView) {
        var change = a.getTag().toString()
        Glide.with(this).load(a.getTag().toString()).into(b)
        Glide.with(this).load(b.getTag().toString()).into(a)
        a.setTag(b.getTag().toString())
        b.setTag(change)

    }

    fun shuffle(a: List<String>) {

        Collections.shuffle(a)
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

    fun success(a: List<String>): Boolean {
        if (a[0] == one.getTag().toString() && a[1] == two.getTag().toString() && a[2] == three.getTag().toString() &&
            a[3] == four.getTag().toString() && a[4] == five.getTag().toString() && a[5] == six.getTag().toString() &&
            a[6] == seven.getTag().toString() && a[7] == eight.getTag().toString()
        ) {
            return true
        } else
            return false
    }

}

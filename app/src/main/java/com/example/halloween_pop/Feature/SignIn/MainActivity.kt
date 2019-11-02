package com.example.halloween_pop.Feature.SignIn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.halloween_pop.Data.SignInData
import com.example.halloween_pop.Feature.API.RequestSignInData
import com.example.halloween_pop.Feature.API.SopkatonServiceImpl
import com.example.halloween_pop.Feature.Menu.MenuActivity
import com.example.halloween_pop.Feature.SignUp.SignUpActivity
import com.example.halloween_pop.R
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var id : String
    private lateinit var pwd : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initController()
    }

    private fun initController() {
        //로그인
        btnMainSignIn.setOnClickListener {
            id = edtMainId.text.toString()
            pwd = edtMainPwd.text.toString()

            //공백 처리
            if(id.isEmpty() || pwd.isEmpty()) {
                Toast.makeText(this, "공백 있음", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //서버 통신
            callBack()
        }

        //회원가입
        btnMainSignUp?.setOnClickListener {
            edtMainId.setText("")
            edtMainPwd.setText("")

            val intent = Intent(this@MainActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

        //게스트 입장
        btnMainGuest?.setOnClickListener{
            val intent = Intent(this@MainActivity, MenuActivity::class.java)
            startActivity(intent)
        }
    }

    private fun callBack() {
        SopkatonServiceImpl.service.requestSignIn(
            RequestSignInData(
                id,
                pwd
            )
        ).enqueue(
            object : Callback<SignInData> {
                //통신 실패
                override fun onFailure(call: Call<SignInData>, t: Throwable) {
                    Log.e("Sign_In_Fail", t.toString())
                }

                override fun onResponse(call: Call<SignInData>, response: Response<SignInData>) {
                    if(response.isSuccessful) {
                        Toast.makeText(applicationContext, "정상 로그인", Toast.LENGTH_SHORT).show()

                        //정상 로그인
                        val intent = Intent(this@MainActivity, MenuActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        Toast.makeText(applicationContext, "로그인 실패", Toast.LENGTH_SHORT).show() //로그인 실패
                    }
                }
            }
        )
    }
}

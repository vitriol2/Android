package com.example.halloween_pop.Feature.SignUp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.halloween_pop.Data.SignUpData
import com.example.halloween_pop.R
import com.example.halloween_pop.Feature.API.SignupRequestData
import com.example.halloween_pop.Feature.API.SopkatonServiceImpl
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    lateinit var id : String
    lateinit var pwd : String
    lateinit var nick : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signUp()
    }

    private fun signUp() {
        btnSignUpOk?.setOnClickListener{
            id = edtSignUpId.text.toString()
            pwd = edtSignUpPwd.text.toString()
            nick = edtSignUpNick.text.toString()

            //공백 처리
            if (id.isEmpty() || pwd.isEmpty() || nick.isEmpty()) {
                Toast.makeText(this, "모든 항목을 입력하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //서버 통신
            callBack()
        }
    }

    private fun callBack() {
        SopkatonServiceImpl.service.requestSignUp(
            SignupRequestData(
                id,
                pwd,
                nick
            )
        ).enqueue(
            object : Callback<SignUpData> {
                //통신 실패
                override fun onFailure(call: Call<SignUpData>, t: Throwable) {
                    Log.e("Sign_Up_Fail", t.toString())
                }

                //통신 성공
                override fun onResponse(call: Call<SignUpData>, response: Response<SignUpData>) {
                    Log.e("Sign_Up_Flag", "${response.message()} ${response.body()}")
                    if(response.isSuccessful) {
                        Toast.makeText(applicationContext, "회원가입 성공", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    else {
                        val msg = response.body().toString()
                        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        )
    }
}

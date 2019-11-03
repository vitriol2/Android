package com.example.halloween_pop.Feature.API

import com.example.halloween_pop.Data.SignInData
import com.example.halloween_pop.Data.SignUpData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SopkatonService {

    @POST("/api/auth/signin")
    fun requestSignIn(
        @Body body: RequestSignInData
    ) : Call<SignInData>

    @POST("/api/auth/signup")
    fun requestSignUp(
        @Body body: SignupRequestData
    ) : Call<SignUpData>
}

//로그인시 내가 서버로 넘기는 데이터
data class RequestSignInData(
    val id: String,
    val password: String
)

//회원가입시 내가 넘기는 데이터
data class SignupRequestData(
    val id: String,
    val password: String,
    val nickname: String
)
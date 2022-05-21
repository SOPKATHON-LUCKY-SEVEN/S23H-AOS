package com.sopt.soptkathon.data.remote.api

import com.sopt.soptkathon.data.remote.request.RequestWrite
import com.sopt.soptkathon.data.remote.response.ResponseWrite
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/letter")
    fun postSignUp(
        @Body body: RequestWrite
    ): Call<ResponseWrite>
}

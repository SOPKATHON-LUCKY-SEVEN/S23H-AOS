package com.sopt.soptkathon.data.remote.api

import com.sopt.soptkathon.data.remote.request.RequestUser
import com.sopt.soptkathon.data.remote.response.ResponseLetterList
import com.sopt.soptkathon.data.remote.response.ResponseUser
import com.sopt.soptkathon.data.remote.response.ResponseWrapper
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("/user")
    suspend fun signUp(
        @Body requestUser: RequestUser
    ): Response<ResponseWrapper<ResponseUser>>

    @GET("/letter/{userId}")
    suspend fun getLetterList(
        @Path("userId") userId: String,
    ): Response<ResponseWrapper<List<ResponseLetterList>>>
}

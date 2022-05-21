package com.sopt.soptkathon.data.remote

import com.sopt.soptkathon.data.remote.api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "http://3.34.179.22:8000"

    val customRetrofit: ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}

package com.sopt.soptkathon.data.remote.response

data class ResponseWrapper<T>(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: T? = null
)

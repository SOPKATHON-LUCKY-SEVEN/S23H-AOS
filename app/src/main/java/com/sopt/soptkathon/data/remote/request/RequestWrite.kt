package com.sopt.soptkathon.data.remote.request

data class RequestWrite(
    val content: String,
    val sender: String,
    val receiver: String
)

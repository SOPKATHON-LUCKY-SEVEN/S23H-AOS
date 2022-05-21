package com.sopt.soptkathon.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseMain(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data: List<Data>
) {
    data class Data(
        @SerializedName("_id")
        val id : String,
        val name: String,
        val phoneNumber : String
    )
}

package com.sopt.soptkathon.data.main

data class ResponseMain(
        val data : List<Data>
){
        data class Data(
                val name : String,
                val profile : String
        )
}
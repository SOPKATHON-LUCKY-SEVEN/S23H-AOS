package com.sopt.soptkathon.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.soptkathon.data.remote.RetrofitBuilder
import com.sopt.soptkathon.data.remote.response.ResponseMain
import com.sopt.soptkathon.util.enqueueUtil

class MainViewModel : ViewModel() {
    private var _friendData = MutableLiveData<List<ResponseMain.Data>>()
    val friendData: LiveData<List<ResponseMain.Data>> get() = _friendData

    fun getFriendList() {
        val call = RetrofitBuilder.customRetrofit.getFreindList("권용민")
        call.enqueueUtil(
            onSuccess = {
                _friendData.value = it.data
            }
        )
    }
}
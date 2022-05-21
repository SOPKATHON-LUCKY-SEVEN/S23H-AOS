package com.sopt.soptkathon.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.soptkathon.data.remote.response.ResponseMain

class MainViewModel : ViewModel() {
    private var _friendData = MutableLiveData<List<ResponseMain.Data>>()
    private val friendData: LiveData<List<ResponseMain.Data>> get() = _friendData

    fun getFriendList() {
        //TODO
    }
}
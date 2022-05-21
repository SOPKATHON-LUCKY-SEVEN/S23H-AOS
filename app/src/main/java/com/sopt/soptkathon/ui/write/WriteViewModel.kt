package com.sopt.soptkathon.ui.write

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WriteViewModel:ViewModel() {
    var etLetter = MutableLiveData<String>()
    var canClick = MutableLiveData<Boolean>()

    fun checkClick() : Boolean{
        return if(!etLetter.value.isNullOrBlank()){
            canClick.value=true
            true
        } else{
            true
        }
    }

}
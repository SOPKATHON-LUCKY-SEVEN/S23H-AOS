package com.sopt.soptkathon.ui.main

import androidx.lifecycle.*
import com.sopt.soptkathon.data.login.LoginRepository
import com.sopt.soptkathon.data.remote.RetrofitBuilder
import com.sopt.soptkathon.data.remote.response.ResponseMain
import com.sopt.soptkathon.util.enqueueUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel constructor(
    userRepository: LoginRepository
) : ViewModel() {
    private var _friendData = MutableLiveData<List<ResponseMain.Data>>()
    private val _user: MutableStateFlow<Pair<String?, String?>> = MutableStateFlow(Pair("", ""))
    val friendData: LiveData<List<ResponseMain.Data>> get() = _friendData
    val user = _user.asStateFlow()

    init {
        viewModelScope.launch {
            userRepository.getUser().collect {
                _user.value = it!!
            }
        }
    }

    fun getFriendList() {
        val call = RetrofitBuilder.customRetrofit.getFreindList(user.value.first!!)
        call.enqueueUtil(
            onSuccess = {
                _friendData.value = it.data
            }
        )
    }
}

class MainViewModelFactory(private val repository: LoginRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
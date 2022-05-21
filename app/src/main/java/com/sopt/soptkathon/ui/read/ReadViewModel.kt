package com.sopt.soptkathon.ui.read

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sopt.soptkathon.data.login.LoginRepository
import com.sopt.soptkathon.data.remote.response.ResponseLetter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ReadViewModel constructor(
    private val userRepository: LoginRepository
) : ViewModel() {
    private val _user: MutableStateFlow<Pair<String?, String?>> = MutableStateFlow(Pair("", ""))
    private val _letterList: MutableStateFlow<List<ResponseLetter>> = MutableStateFlow(emptyList())
    private val _titleText: MutableStateFlow<String> = MutableStateFlow("")
    val letterList = _letterList.asStateFlow()
    val user = _user.asStateFlow()
    val titleText = _titleText.asStateFlow()

    init {
        viewModelScope.launch {
            userRepository.getUser().collect {
                _user.value = it!!
            }
        }
    }

    fun getList() {
        viewModelScope.launch {
            val response = userRepository.getLetterList(user.value.first!!)
            if (response.isSuccessful) {
                Log.d("sadjfkksjf","${response.body()!!.data!!}")
                _letterList.value = response.body()!!.data!!
            } else {
                Log.d("ViewModelssss", "viewmodel : ${response.errorBody()!!.string()}")
            }
            _titleText.value = String.format(
                "이번 주 %s 님의 밤하늘은 \n%d명의 감사로 빛나고 있어요",
                user.value.second,
                letterList.value.size
            )
        }
    }
}

class ReadViewModelFactory(private val repository: LoginRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReadViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ReadViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
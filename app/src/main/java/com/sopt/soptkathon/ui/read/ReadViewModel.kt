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
    userRepository: LoginRepository
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
            val response = userRepository.getLetterList("")
            if (response.isSuccessful) {
                _letterList.value = response.body()!!.data!!
                _titleText.value = String.format(
                    "이번 주 %s 님의 밤하늘은 %d명의 감사로 빛나고 있어요",
                    user.value.first,
                    letterList.value.size
                )
            } else {
                Log.d("ViewModelssss", "viewmodel : ${response.errorBody()!!.string()}")
            }
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
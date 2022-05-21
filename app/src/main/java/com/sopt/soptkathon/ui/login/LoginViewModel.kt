package com.sopt.soptkathon.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.sopt.soptkathon.data.login.LoginRepository
import java.util.regex.Pattern
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class LoginViewModel constructor(
    private val repository: LoginRepository
) : ViewModel() {
    private val _eventFlow = MutableSharedFlow<LoginEvent>()

    val name = MutableStateFlow("")
    val phoneNumber = MutableStateFlow("")
    val isInputEmpty: LiveData<Boolean> = name.combine(phoneNumber) { name, phoneNumber ->
        name.isNotEmpty() && phoneNumber.isNotEmpty()
    }.asLiveData()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            repository.isAutoLogin().collect { userId ->
                if (userId != -1) {
                    emitEvent(LoginEvent.GoMain)
                }
            }
        }
    }

    fun login() {
        if (validate(name.value, phoneNumber.value) != null) {
            emitEvent(LoginEvent.ShowToast(validate(name.value, phoneNumber.value)!!))
        } else {
            viewModelScope.launch {
                // response 추가

                repository.setAutoLogin(1)
            }
        }
    }

    private fun validate(name: String, phoneNumber: String): String? {
        return when {
            !Pattern.matches("^[가-힣]*\$", name) -> "이름을 다시 입력해주세요"
            !Pattern.matches(
                "^01([0|1|6|7|8|9])([0-9]{3,4})([0-9]{4})\$",
                phoneNumber
            ) -> "전화번호를 다시 입력해주세요"
            else -> null
        }
    }

    private fun emitEvent(event: LoginEvent) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }
}

sealed class LoginEvent {
    data class ShowToast(val msg: String) : LoginEvent()
    object GoMain : LoginEvent()
}

class LoginViewModelFactory(private val repository: LoginRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
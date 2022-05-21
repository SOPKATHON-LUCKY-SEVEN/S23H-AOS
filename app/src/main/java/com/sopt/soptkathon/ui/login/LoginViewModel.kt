package com.sopt.soptkathon.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import java.util.regex.Pattern
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _eventFlow = MutableSharedFlow<LoginEvent>()
    private val _name = MutableStateFlow("")
    private val _phoneNumber = MutableStateFlow("")

    val name get() = _name
    val phoneNumber get() = _phoneNumber
    val eventFlow = _eventFlow.asSharedFlow()

    fun login() {
        if (validate(name.value, phoneNumber.value) != null) {
            emitEvent(LoginEvent.ShowToast(validate(name.value, phoneNumber.value)!!))
        } else {
            emitEvent(LoginEvent.GoMain)
        }
    }

    private fun validate(name: String, phoneNumber: String): String? {
        return when {
            name.isEmpty() -> "이름을 입력해주세요"
            !Pattern.matches("^[가-힣]*\$", name) -> "유효한 이름을 입력해주세요"
            phoneNumber.isEmpty() -> "전화번호를 입력해주세요"
            !Pattern.matches(
                "^01([0|1|6|7|8|9])([0-9]{3,4})([0-9]{4})\$",
                phoneNumber
            ) -> "유효한 전화번호를 입력해주세요"
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
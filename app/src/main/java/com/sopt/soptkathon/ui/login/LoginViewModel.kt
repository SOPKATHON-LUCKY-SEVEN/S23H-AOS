package com.sopt.soptkathon.ui.login

import android.util.Log
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
    val uiEventFlow = _eventFlow.asSharedFlow()

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
        Log.d("asdfasfas","${isInputEmpty.value}")
    }

    private fun emitEvent(event: LoginEvent) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }
}

sealed class LoginEvent {
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

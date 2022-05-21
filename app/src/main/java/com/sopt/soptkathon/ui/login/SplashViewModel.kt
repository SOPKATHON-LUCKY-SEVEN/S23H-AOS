package com.sopt.soptkathon.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sopt.soptkathon.data.login.LoginRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SplashViewModel constructor(
    private val repository: LoginRepository
) : ViewModel() {
    private val _eventFlow = MutableSharedFlow<SplashEvent>()
    val uiEventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            delay(1000)
            repository.getUser().collect { userId ->
                if (userId!!.first != null) {
                    emitEvent(SplashEvent.GoMain)
                } else {
                    emitEvent(SplashEvent.GoSignUp)
                }
            }
        }
    }

    private fun emitEvent(event: SplashEvent) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }
}

class SplashViewModelFactory(private val repository: LoginRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SplashViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

sealed class SplashEvent {
    object GoMain : SplashEvent()
    object GoSignUp : SplashEvent()
}
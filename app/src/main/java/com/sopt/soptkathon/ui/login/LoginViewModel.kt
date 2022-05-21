package com.sopt.soptkathon.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.sopt.soptkathon.data.login.LoginRepository
import com.sopt.soptkathon.data.remote.request.RequestUser
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

    fun login() {
        viewModelScope.launch {
            val response = repository.signUp(RequestUser(name.value, phoneNumber.value))
            if (response.isSuccessful) {
                repository.setUser(response.body()!!.data!!._id, name.value)
                emitEvent(LoginEvent.GoMain)
            } else {
                Log.d("ViewModelssss", "viewmodel : ${response.errorBody()!!.string()}")
                emitEvent(LoginEvent.ShowToast("중복된 유저입니다"))
            }
        }
    }

    private fun emitEvent(event: LoginEvent) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }
}

sealed class LoginEvent {
    object GoMain : LoginEvent()
    data class ShowToast(val msg: String) : LoginEvent()
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
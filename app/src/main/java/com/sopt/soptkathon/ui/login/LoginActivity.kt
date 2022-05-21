package com.sopt.soptkathon.ui.login

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sopt.soptkathon.R
import com.sopt.soptkathon.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView<ActivityLoginBinding?>(this, R.layout.activity_login)
                .apply {
                    vm = viewModel
                    lifecycleOwner = this@LoginActivity
                }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event)
                }
            }
        }
    }

    private fun handleEvent(loginEvent: LoginEvent) {
        when (loginEvent) {
            is LoginEvent.ShowToast -> {
                Toast.makeText(
                    this,
                    loginEvent.msg,
                    Toast.LENGTH_SHORT
                ).show()
            }
            is LoginEvent.GoMain -> {
                // 이동
                Log.d("asdfasdfs", "GoMain")
            }
        }
    }
}
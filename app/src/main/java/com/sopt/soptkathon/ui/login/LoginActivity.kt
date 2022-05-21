package com.sopt.soptkathon.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sopt.soptkathon.MainApp
import com.sopt.soptkathon.R
import com.sopt.soptkathon.databinding.ActivityLoginBinding
import com.sopt.soptkathon.ui.main.MainActivity
import com.sopt.soptkathon.util.shortToast
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory((application as MainApp).repository)
    }

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
                shortToast(loginEvent.msg)
            }
            is LoginEvent.GoMain -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}

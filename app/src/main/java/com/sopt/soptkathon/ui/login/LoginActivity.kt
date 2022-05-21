package com.sopt.soptkathon.ui.login

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sopt.soptkathon.MainApp
import com.sopt.soptkathon.R
import com.sopt.soptkathon.databinding.ActivityLoginBinding
import java.util.regex.Pattern
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

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

        viewModel.uiEventFlow
            .flowWithLifecycle(lifecycle)
            .onEach(this::handleEvent)
            .launchIn(lifecycleScope)

        binding.btnLoginLogin.setOnClickListener {
            if (!Pattern.matches("^[가-힣]*\$", viewModel.name.value)) binding.tilLogin.error =
                "전화번호를 다시 입력해주세요" else binding.tilLogin.error = null
            if (!Pattern.matches(
                    "^01([0|1|6|7|8|9])([0-9]{3,4})([0-9]{4})\$",
                    viewModel.phoneNumber.value
                )
            ) binding.tilLoginPhone.error = "전화번호를 다시 입력해주세요" else binding.tilLoginPhone.error =
                null
        }
    }

    private fun handleEvent(loginEvent: LoginEvent) {
        when (loginEvent) {
            is LoginEvent.GoMain -> {
                Log.d("asdfasfas", "asdfmlaksmf;")
//                startActivity(Intent(this, MainActivity::class.java))
//                finish()
            }
        }
    }
}
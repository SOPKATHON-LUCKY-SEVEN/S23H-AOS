package com.sopt.soptkathon.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sopt.soptkathon.MainApp
import com.sopt.soptkathon.R
import com.sopt.soptkathon.ui.main.MainActivity
import com.sopt.soptkathon.util.colorOf
import com.sopt.soptkathon.util.setStatusBarColor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModels {
        SplashViewModelFactory((application as MainApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setStatusBarColor(colorOf(R.color.navy_313249))
        viewModel.uiEventFlow
            .flowWithLifecycle(lifecycle)
            .onEach(this::handleEvent)
            .launchIn(lifecycleScope)
    }

    private fun handleEvent(loginEvent: SplashEvent) {
        when (loginEvent) {
            is SplashEvent.GoMain -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            is SplashEvent.GoSignUp -> {
                startActivity(Intent(this, OnBoardingActivity::class.java))
                finish()
            }
        }
    }
}
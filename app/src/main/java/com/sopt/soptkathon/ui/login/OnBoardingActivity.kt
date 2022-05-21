package com.sopt.soptkathon.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.sopt.soptkathon.R
import com.sopt.soptkathon.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_on_boarding)
        binding.btnOnboardSend.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
}
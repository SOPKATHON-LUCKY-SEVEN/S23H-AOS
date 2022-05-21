package com.sopt.soptkathon.ui.write

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sopt.soptkathon.R
import com.sopt.soptkathon.databinding.ActivityWriteBinding
import com.sopt.soptkathon.ui.main.MainActivity
import com.sopt.soptkathon.util.colorOf
import com.sopt.soptkathon.util.setStatusBarColor
import com.sopt.soptkathon.util.shortToast

class WriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor(colorOf(R.color.purple_D6B6D4))
        binding = ActivityWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvWriteBtnsend.isEnabled = false
        clickEvent()
        activateBtn()
    }

    private fun clickEvent() {
        with(binding) {
            ivWriteBtnback.setOnClickListener {
                finish()
            }
            tvWriteBtnsend.setOnClickListener {
                val intent = Intent(this@WriteActivity, MainActivity::class.java)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }

    private fun activateBtn() {
        with(binding) {
            if (etWriteLetter.text.isEmpty() or etWriteFromwho.text.isEmpty()) {
                tvWriteBtnsend.isEnabled = false
                shortToast("닉네임과 내용을 적어주세요!")
            } else tvWriteBtnsend.isEnabled = true
        }
    }
}

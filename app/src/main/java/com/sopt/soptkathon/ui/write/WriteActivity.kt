package com.sopt.soptkathon.ui.write

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sopt.soptkathon.data.remote.RetrofitBuilder
import com.sopt.soptkathon.data.remote.request.RequestWrite
import com.sopt.soptkathon.databinding.ActivityWriteBinding
import com.sopt.soptkathon.ui.main.MainActivity
import com.sopt.soptkathon.util.enqueueUtil
import com.sopt.soptkathon.util.shortToast

class WriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // binding.tvWriteBtnsend.isEnabled = false
        // activateBtn()
        clickEvent()
    }

    private fun clickEvent() {
        with(binding) {
            ivWriteBtnback.setOnClickListener {
                finish()
            }
            tvWriteBtnsend.setOnClickListener {
                val requestWrite = RequestWrite(
                    content = etWriteLetter.text.toString(),
                    sender = etWriteFromwho.text.toString(),
                    receiver = tvWriteTo.text.toString()
                )

                val call = RetrofitBuilder.customRetrofit.postWrite(requestWrite)

                call.enqueueUtil(
                    onSuccess = {
                        Log.d("원트?", it._id)
                    }
                )

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

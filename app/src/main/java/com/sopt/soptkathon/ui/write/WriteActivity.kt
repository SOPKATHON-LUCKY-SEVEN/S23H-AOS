package com.sopt.soptkathon.ui.write

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.sopt.soptkathon.R
import com.sopt.soptkathon.data.remote.RetrofitBuilder
import com.sopt.soptkathon.data.remote.request.RequestWrite
import com.sopt.soptkathon.databinding.ActivityWriteBinding
import com.sopt.soptkathon.ui.main.MainActivity
import com.sopt.soptkathon.ui.main.MainActivity.Companion.KEY_FRIEND_NAME
import com.sopt.soptkathon.util.colorOf
import com.sopt.soptkathon.util.enqueueUtil
import com.sopt.soptkathon.util.setStatusBarColor
import com.sopt.soptkathon.util.shortToast

class WriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWriteBinding
    private val viewModel: WriteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor(colorOf(R.color.purple_D6B6D4))
        binding = ActivityWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        binding.tvWriteTowho.text = intent.getStringExtra(MainActivity.KEY_FRIEND_NAME)
        checkButton()
        clickEvent()
    }

    private fun clickEvent() {
        val content: String = intent.getStringExtra(MainActivity.KEY_FRIEND_ID) ?: ""
        with(binding) {
            ivWriteBtnback.setOnClickListener {
                finish()
            }
            tvWriteBtnsend.setOnClickListener {
                var sender = etWriteFromwho.text.toString()
                if(sender==null) sender="익명"
                val requestWrite = RequestWrite(
                    content = content,
                    sender = sender,
                    receiver = tvWriteTo.text.toString()
                )

                val call = RetrofitBuilder.customRetrofit.postWrite(requestWrite)

                call.enqueueUtil(
                    onSuccess = {
                        Log.d("원트?", it._id)
                    }
                )

                val intent = Intent(this@WriteActivity, MainActivity::class.java)
                intent.putExtra(KEY_FRIEND_NAME, binding.tvWriteTowho.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }

    private fun initViewModel(){
        binding.viewModel = viewModel
        binding.lifecycleOwner=this
    }

    private fun checkButton(){
        binding.etWriteLetter.doAfterTextChanged {
            binding.tvWriteBtnsend.isEnabled = it?.isNotEmpty() ?: false
        }
    }

}

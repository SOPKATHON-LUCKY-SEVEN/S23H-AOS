package com.sopt.soptkathon.ui.read

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sopt.soptkathon.MainApp
import com.sopt.soptkathon.R
import com.sopt.soptkathon.databinding.ActivityReadBinding

class ReadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReadBinding
    private val viewModel: ReadViewModel by viewModels {
        ReadViewModelFactory((application as MainApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityReadBinding?>(this, R.layout.activity_read)
            .apply {
                vm = viewModel
                lifecycleOwner = this@ReadActivity
            }

        
    }
}
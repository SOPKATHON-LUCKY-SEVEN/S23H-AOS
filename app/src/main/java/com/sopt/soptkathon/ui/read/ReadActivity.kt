package com.sopt.soptkathon.ui.read

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.soptkathon.MainApp
import com.sopt.soptkathon.R

class ReadActivity : AppCompatActivity() {
    private val viewModel: ReadViewModel by viewModels {
        ReadViewModelFactory((application as MainApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)
    }
}
package com.sopt.soptkathon.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.soptkathon.databinding.ActivityMainBinding
import com.sopt.soptkathon.ui.write.WriteActivity
import com.sopt.soptkathon.util.shortToast

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setResultWriting()
        clickFab()
        initAdapter()
    }

    private fun setResultWriting() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    this.shortToast("별이 보내졌습니다.")
                } else {
                    this.shortToast("별 보내기를 취소했습니다.")
                }
            }
    }

    private fun clickFab() {
        binding.fabMain.setOnClickListener {
            val intent = Intent(this, WriteActivity::class.java)
            //intent.putExtra()
            resultLauncher.launch(intent)
        }
    }

    private fun initAdapter() {
        adapter = MainAdapter()
        binding.rvFriendList.adapter = adapter
        binding.rvFriendList.addItemDecoration(VerticalItemDecoration())
        //adapter.submitList()
    }
}
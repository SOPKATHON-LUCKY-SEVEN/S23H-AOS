package com.sopt.soptkathon.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sopt.soptkathon.MainApp
import com.sopt.soptkathon.R
import com.sopt.soptkathon.data.remote.response.ResponseMain
import com.sopt.soptkathon.databinding.ActivityMainBinding
import com.sopt.soptkathon.ui.read.ReadActivity
import com.sopt.soptkathon.ui.read.ReadViewModelFactory
import com.sopt.soptkathon.ui.write.WriteActivity
import com.sopt.soptkathon.util.colorOf
import com.sopt.soptkathon.util.setStatusBarColor
import com.sopt.soptkathon.util.shortToast

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter1: MainAdapter
    private val viewModel by viewModels<MainViewModel>{
        MainViewModelFactory((application as MainApp).repository)
    }
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor(colorOf(R.color.purple_D6B6D4))
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setResultWriting()
        getFriendList()
        initAdapter()
        clickFab()
    }

    private fun setResultWriting() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val name = result.data?.getStringExtra(KEY_FRIEND_NAME)
                    this.shortToast("${name}님에게 감사의 별을 보냈어요!")
                } else {
                    this.shortToast("별 보내기를 취소했습니다.")
                }
            }
    }

    private fun clickFab() {
        binding.fabMain.setOnClickListener {
            val intent = Intent(this, ReadActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onClickItem(data: ResponseMain.Data) {
        val intent = Intent(this, WriteActivity::class.java)
        intent.putExtra(KEY_FRIEND_ID, data.id)
        intent.putExtra(KEY_FRIEND_NAME, data.name)
        resultLauncher.launch(intent)
    }

    private fun getFriendList() {
        viewModel.getFriendList()
    }

    private fun initAdapter() {
        adapter1 = MainAdapter { onClickItem(it) }
        binding.rvFriendList.adapter = adapter1
        binding.rvFriendList.addItemDecoration(VerticalItemDecoration())
        viewModel.friendData.observe(this) {
            adapter1.submitList(viewModel.friendData.value)
        }
    }

    companion object {
        const val KEY_FRIEND_ID = "KEY_FRIEND_ID"
        const val KEY_FRIEND_NAME = "KEY_FRIEND_NAME"
    }
}

package com.sopt.soptkathon.ui.read

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sopt.soptkathon.MainApp
import com.sopt.soptkathon.R
import com.sopt.soptkathon.databinding.ActivityReadBinding
import com.sopt.soptkathon.util.CustomDialog
import com.sopt.soptkathon.util.colorOf
import com.sopt.soptkathon.util.setStatusBarColor

class ReadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReadBinding
    private val viewModel: ReadViewModel by viewModels {
        ReadViewModelFactory((application as MainApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor(colorOf(R.color.purple_D6B6D4))
        binding = DataBindingUtil.setContentView<ActivityReadBinding?>(this, R.layout.activity_read)
            .apply {
                lifecycleOwner = this@ReadActivity
            }
        viewModel.getList()
        with(binding) {
            val count = viewModel.letterList.value.size
            ivReadStar1.visibility = if (count > 0) View.INVISIBLE else View.VISIBLE
            ivReadStar2.visibility = if (count > 1) View.INVISIBLE else View.VISIBLE
            ivReadStar3.visibility = if (count > 2) View.INVISIBLE else View.VISIBLE
            ivReadStar4.visibility = if (count > 3) View.INVISIBLE else View.VISIBLE

            ivReadStar1.setOnClickListener {
                val customDialog = CustomDialog(this@ReadActivity)
                customDialog.showDialog(
                    R.layout.dialog_letter,
                    viewModel.letterList.value[0].content,
                    viewModel.letterList.value[0].sender
                )
            }
            ivReadStar2.setOnClickListener {
                val customDialog = CustomDialog(this@ReadActivity)
                customDialog.showDialog(
                    R.layout.dialog_letter,
                    viewModel.letterList.value[1].content,
                    viewModel.letterList.value[1].sender
                )
            }
            ivReadStar3.setOnClickListener {
                val customDialog = CustomDialog(this@ReadActivity)
                customDialog.showDialog(
                    R.layout.dialog_letter,
                    viewModel.letterList.value[2].content,
                    viewModel.letterList.value[2].sender
                )
            }
            ivReadStar4.setOnClickListener {
                val customDialog = CustomDialog(this@ReadActivity)
                customDialog.showDialog(
                    R.layout.dialog_letter,
                    viewModel.letterList.value[3].content,
                    viewModel.letterList.value[3].sender
                )
            }
        }

    }
}
package com.sopt.soptkathon.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.soptkathon.R
import com.sopt.soptkathon.data.remote.response.ResponseMain
import com.sopt.soptkathon.databinding.ItemFriendListBinding

class MainAdapter(private val onFriendClick: ((ResponseMain.Data) -> Unit)? = null) :
    ListAdapter<ResponseMain.Data, MainAdapter.MainViewHolder>(MainComparator()) {

    class MainViewHolder(private val binding: ItemFriendListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseMain.Data, onFriendClick: ((ResponseMain.Data) -> Unit)? = null) {
            binding.friendData = data
            val imgList = arrayListOf<Int>(
                R.drawable.ic_star_sign_1,
                R.drawable.ic_star_sign_2,
                R.drawable.ic_star_sign_3,
                R.drawable.ic_star_sign_4,
            )
            val randomInt = (0..3).random()
            Glide.with(binding.ivProfile.context).load(imgList[randomInt]).into(binding.ivProfile)
            binding.btnSendStar.setOnClickListener {
                onFriendClick?.invoke(data)
            }
        }
    }

    private class MainComparator() : DiffUtil.ItemCallback<ResponseMain.Data>() {
        override fun areItemsTheSame(
            oldItem: ResponseMain.Data,
            newItem: ResponseMain.Data
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: ResponseMain.Data,
            newItem: ResponseMain.Data
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFriendListBinding.inflate(layoutInflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        return holder.onBind(getItem(position), onFriendClick)
    }
}
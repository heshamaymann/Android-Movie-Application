package com.example.movieapp.features.activity.second.navdrawer

import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.FragmentNavDrawerBinding

class NavViewHolder(private val binding: FragmentNavDrawerBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MenuItem) {
        binding.item.text = item.name
        binding.itemimg.setImageResource(item.img)
    }


    fun onClick(onClick: (MenuItem) -> Unit, item: MenuItem) {
        binding.root.setOnClickListener {
            onClick.invoke(item)
        }
    }


}


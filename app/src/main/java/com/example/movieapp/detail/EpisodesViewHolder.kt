package com.example.movieapp.detail

import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ListEpisodesBinding

class EpisodesViewHolder (private val binding: ListEpisodesBinding) :
    RecyclerView.ViewHolder(binding.root){
        fun bind(item : String)
        {
            binding.eps.text = item
        }
    fun onClick(onClick: (String) -> Unit, item: String) {
        binding.root.setOnClickListener {
            onClick.invoke(item)
        }
    }
}
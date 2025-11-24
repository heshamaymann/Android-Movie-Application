package com.example.movieapp.detail

import androidx.recyclerview.widget.DiffUtil

class EpisodesDiffUtil
    : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return  oldItem == newItem
        }

    }

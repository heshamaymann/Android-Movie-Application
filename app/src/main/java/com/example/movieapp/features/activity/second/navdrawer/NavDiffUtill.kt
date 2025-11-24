package com.example.movieapp.features.activity.second.navdrawer

import androidx.recyclerview.widget.DiffUtil

class NavDiffUtill  : DiffUtil.ItemCallback<MenuItem>() {
    override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
        return  oldItem == newItem
    }

}
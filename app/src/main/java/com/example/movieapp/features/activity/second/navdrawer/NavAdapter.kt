package com.example.movieapp.features.activity.second.navdrawer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.movieapp.databinding.FragmentNavDrawerBinding

class NavAdapter( private val onClick: (MenuItem) -> Unit
) : ListAdapter<MenuItem, NavViewHolder>(NavDiffUtill()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavViewHolder {
        val binding =
            FragmentNavDrawerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NavViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NavViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.onClick(onClick, getItem(position))
    }
}
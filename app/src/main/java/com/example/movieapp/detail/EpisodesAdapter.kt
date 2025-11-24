package com.example.movieapp.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.movieapp.databinding.ListEpisodesBinding

class EpisodesAdapter(private val onClick: (String) -> Unit)
    : ListAdapter<String, EpisodesViewHolder>(EpisodesDiffUtil()){
    val episodList :MutableList<String> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        val binding =
            ListEpisodesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.onClick(onClick, getItem(position))
    }

}

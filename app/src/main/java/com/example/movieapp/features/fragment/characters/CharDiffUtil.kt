package com.example.movieapp.features.fragment.characters

import androidx.recyclerview.widget.DiffUtil
import com.example.movieapp.features.fragment.ItemCharacter

class CharDiffUtil
    : DiffUtil.ItemCallback<ItemCharacter>() {
    override fun areItemsTheSame(oldItem: ItemCharacter, newItem: ItemCharacter): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ItemCharacter, newItem: ItemCharacter): Boolean {
          return  oldItem == newItem
    }

}

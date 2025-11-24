package com.example.movieapp.features.fragment.characters

import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.core.Util.loadImage
import com.example.movieapp.databinding.ListItemCharactersBinding
import com.example.movieapp.features.fragment.ItemCharacter

class CharViewHolder(private val binding: ListItemCharactersBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ItemCharacter) {
        binding.descText.text = item.name
        binding.imgCharacter.loadImage(item.image)
    }


    fun onClick(onClick: (ItemCharacter) -> Unit, item: ItemCharacter) {
        binding.root.setOnClickListener {
            onClick.invoke(item)
        }
    }


}

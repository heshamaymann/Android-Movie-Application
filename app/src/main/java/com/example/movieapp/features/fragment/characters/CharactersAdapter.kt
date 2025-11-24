
package com.example.movieapp.features.fragment.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.movieapp.databinding.ListItemCharactersBinding
import com.example.movieapp.features.fragment.ItemCharacter

class CharactersAdapter(
    private val onClick: (ItemCharacter) -> Unit
) : ListAdapter<ItemCharacter, CharViewHolder>(CharDiffUtil()) {

     val mList :MutableList<ItemCharacter> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharViewHolder {
        val binding =
            ListItemCharactersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.onClick(onClick, getItem(position))
    }

    fun setList(list: List<ItemCharacter>) {
        mList.clear()
        mList.addAll(list)
        submitList(mList.toMutableList())
        notifyItemRangeChanged(0, mList.size)
        }

}


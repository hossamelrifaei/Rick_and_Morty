package com.example.rickandmorty.presentaion.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.CharacterItemBinding
import com.example.rickandmorty.extensions.load
import com.example.rickandmorty.presentaion.home.Character

class CharacterVH(
    private val binding: CharacterItemBinding,
    private val function: (Character) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(character: Character) {
        binding.apply {
            imgAvatar.load(character.image, root.width, root.height)
            root.setOnClickListener {
                function(character)
            }
        }

    }
}

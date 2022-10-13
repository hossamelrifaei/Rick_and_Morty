package com.example.rickandmorty.presentaion.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.CharacterItemBinding
import com.example.rickandmorty.extensions.load
import com.example.rickandmorty.presentaion.home.HomeViewEvents
import model.Character

class CharacterVH(
    private val binding: CharacterItemBinding,
    private val function: (HomeViewEvents) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(character: Character) {
        binding.apply {
            imgAvatar.load(character.image)
            root.setOnClickListener {
                function(HomeViewEvents.OnCharacterSelected(character))
            }
        }

    }
}

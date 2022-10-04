package com.example.rickandmorty.presentaion.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.CharacterItemBinding
import com.example.rickandmorty.extensions.load
import model.Character
import com.example.rickandmorty.presentaion.home.HomeViewEvents
import com.example.rickandmorty.presentaion.home.HomeViewIntentFactory

class CharacterVH(
    private val binding: CharacterItemBinding,
    private val factory: HomeViewIntentFactory
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(character: Character) {
        binding.apply {
            imgAvatar.load(character.image)
            root.setOnClickListener {
                factory.process(HomeViewEvents.OnCharacterSelected(character))
            }
        }

    }
}

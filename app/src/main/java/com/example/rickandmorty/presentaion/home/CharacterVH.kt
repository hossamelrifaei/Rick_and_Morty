package com.example.rickandmorty.presentaion.home

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.CharacterItemBinding

class CharacterVH(val binding: CharacterItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: Character) {
        binding.title.text = character.name
    }


}

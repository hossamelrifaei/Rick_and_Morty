package com.example.rickandmorty.presentaion.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.databinding.CharacterItemBinding
import com.example.rickandmorty.presentaion.home.Character
import com.example.rickandmorty.presentaion.home.HomeViewEvents
import com.example.rickandmorty.presentaion.home.HomeViewIntentFactory
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CharacterPagingAdapter @Inject constructor(
    private val factory: HomeViewIntentFactory,
) : PagingDataAdapter<Character, CharacterVH>(CHARACTER_DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterVH {
        val binding =
            CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterVH(binding) { factory.process(HomeViewEvents.OnCharacterSelected(it)) }
    }


    override fun onBindViewHolder(holder: CharacterVH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    companion object CHARACTER_DIFF_CALLBACK : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Character, newItem: Character) =
            oldItem == newItem
    }

}
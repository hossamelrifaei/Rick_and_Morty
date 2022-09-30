package com.example.rickandmorty.presentaion.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.common.StateSubscriber
import com.example.rickandmorty.databinding.CharacterItemBinding
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@FragmentScoped
class CharactersAdapter @Inject constructor(
    private val factory: HomeViewIntentFactory,
) : RecyclerView.Adapter<CharacterVH>(),
    StateSubscriber<HomeModel> {

    private val job = MainScope()
    private var characters: List<Character> = emptyList()

    init {
        setHasStableIds(true)
    }

    override fun Flow<HomeModel>.subscribeToState() = onEach { model ->
        characters = model.characters
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterVH {
        val binding =
            CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterVH(binding)
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: CharacterVH, position: Int) {
        holder.bind(characters[position])
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        factory.modelState().subscribeToState().launchIn(job)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        job.cancel()
    }
}


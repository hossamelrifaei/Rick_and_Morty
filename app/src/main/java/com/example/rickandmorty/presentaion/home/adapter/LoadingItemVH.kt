package com.example.rickandmorty.presentaion.home.adapter

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.LoadingErrorRetryItemBinding
import com.example.rickandmorty.presentaion.home.HomeViewEvents

class LoadingItemVH(
    private val binding: LoadingErrorRetryItemBinding,
    private val function: (HomeViewEvents) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(state: LoadState) {
        binding.apply {
            progressBar.isVisible = state is LoadState.Loading
            retryButton.isVisible = state is LoadState.Error
            retryButton.setOnClickListener {
                function.invoke(HomeViewEvents.RETRY())
            }
        }
    }
}
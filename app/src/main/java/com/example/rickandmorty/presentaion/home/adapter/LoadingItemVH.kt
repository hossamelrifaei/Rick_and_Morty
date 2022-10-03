package com.example.rickandmorty.presentaion.home.adapter

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.LoadingErrorRetryItemBinding

class LoadingItemVH(
    private val binding: LoadingErrorRetryItemBinding,
    private val function: () -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(state: LoadState) {
        binding.apply {
            progressBar.isVisible = state is LoadState.Loading
            retryButton.isVisible = state is LoadState.Error
            retryButton.setOnClickListener {
                function.invoke()
            }
        }
    }
}
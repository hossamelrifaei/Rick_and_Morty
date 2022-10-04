package com.example.rickandmorty.presentaion.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.LoadingErrorRetryItemBinding
import com.example.rickandmorty.presentaion.home.HomeViewEvents
import com.example.rickandmorty.presentaion.home.HomeViewIntentFactory
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject


@ViewModelScoped
open class CharactersLoadingStateAdapter @Inject constructor(
    val factory: HomeViewIntentFactory,
) : LoadStateAdapter<LoadingItemVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        LoadingItemVH(
            LoadingErrorRetryItemBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.loading_error_retry_item, parent, false)
            )
        ) {
            factory.process(HomeViewEvents.RETRY)
        }

    override fun onBindViewHolder(holder: LoadingItemVH, loadState: LoadState) =
        holder.bind(loadState)
}
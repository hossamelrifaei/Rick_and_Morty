package com.example.rickandmorty.presentaion.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.LoadingErrorRetryItemBinding
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject


@FragmentScoped
open class CharactersLoadingStateAdapter @Inject constructor(
) : LoadStateAdapter<LoadingItemVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        LoadingItemVH(
            LoadingErrorRetryItemBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.loading_error_retry_item, parent, false)
            )
        ) {}

    override fun onBindViewHolder(holder: LoadingItemVH, loadState: LoadState) =
        holder.bind(loadState)
}
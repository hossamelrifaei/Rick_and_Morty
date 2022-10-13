package com.example.rickandmorty.presentaion.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.LoadingErrorRetryItemBinding
import com.example.rickandmorty.presentaion.home.HomeViewEvents
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject


@FragmentScoped
open class CharactersLoadingStateAdapter @Inject constructor(
) : LoadStateAdapter<LoadingItemVH>() {

    lateinit var listener: AdapterEventListener<HomeViewEvents>

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        LoadingItemVH(
            LoadingErrorRetryItemBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.loading_error_retry_item, parent, false)
            )
        ) { listener.onEvent(it) }

    override fun onBindViewHolder(holder: LoadingItemVH, loadState: LoadState) =
        holder.bind(loadState)

    fun addListener(lis: AdapterEventListener<HomeViewEvents>) {
        listener = lis
    }
}
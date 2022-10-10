package com.example.rickandmorty.presentaion.home.adapter

import androidx.paging.PagingData
import androidx.recyclerview.widget.ConcatAdapter
import dagger.hilt.android.scopes.FragmentScoped
import model.Character
import javax.inject.Inject

@FragmentScoped
open class LoadingAdapter @Inject constructor(
    private val pagingAdapter: CharacterPagingAdapter,
    private val loadingStateAdapter: CharactersLoadingStateAdapter,
) : PagingInterface {

    override fun doRetry() {
        pagingAdapter.retry()
    }

    override suspend fun doSubmitData(data: PagingData<Character>) {
        pagingAdapter.submitData(data)
    }

    operator fun invoke(): ConcatAdapter {
        return pagingAdapter.withLoadStateFooter(loadingStateAdapter)
    }
}
package com.example.rickandmorty.presentaion.home.adapter

import androidx.paging.PagingData
import androidx.recyclerview.widget.ConcatAdapter
import com.example.rickandmorty.presentaion.home.Character
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
open class LoadingAdapter @Inject constructor(
    val pagingAdapter: CharacterPagingAdapter,
    val loadingStateAdapter: CharactersLoadingStateAdapter
) : PagingInterface {

    override fun doRetry() {
        pagingAdapter.retry()
    }

    override suspend fun doSubmitData(data: PagingData<Character>) {
        pagingAdapter.submitData(data)
    }

    override fun addLoadingAdapter(): ConcatAdapter {
        return pagingAdapter.withLoadStateFooter(loadingStateAdapter)
    }
}
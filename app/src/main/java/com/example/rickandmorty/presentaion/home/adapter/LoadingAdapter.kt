package com.example.rickandmorty.presentaion.home.adapter

import androidx.paging.PagingData
import androidx.recyclerview.widget.ConcatAdapter
import com.example.rickandmorty.presentaion.home.HomeViewEvents
import dagger.hilt.android.scopes.FragmentScoped
import model.Character
import javax.inject.Inject

@FragmentScoped
open class LoadingAdapter @Inject constructor(
    private val pagingAdapter: CharacterPagingAdapter,
    private val loadingStateAdapter: CharactersLoadingStateAdapter,
) : PagingInterface, AdapterEventListener<HomeViewEvents> {

    val state = pagingAdapter.loadStateFlow
    lateinit var function: (HomeViewEvents) -> Unit

    init {
        loadingStateAdapter.addListener(this)
        pagingAdapter.addListener(this)
    }

    override fun doRetry() {
        pagingAdapter.retry()
    }

    override suspend fun doSubmitData(data: PagingData<Character>) {
        pagingAdapter.submitData(data)
    }

    operator fun invoke(function: (HomeViewEvents) -> Unit): ConcatAdapter {
        this.function = function
        return pagingAdapter.withLoadStateFooter(loadingStateAdapter)
    }

    override fun onEvent(event: HomeViewEvents) {
        function.invoke(event)
    }
}
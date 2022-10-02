package com.example.rickandmorty.presentaion.home

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickandmorty.common.ModelStoreImpl
import com.example.rickandmorty.presentaion.home.adapter.CharactersPagingSource
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject


@ViewModelScoped
class HomeModelStore @Inject constructor(private val pagingSource: CharactersPagingSource) :
    ModelStoreImpl<HomeState>(
        HomeState(
            state = HomeState.State.LOADING,
            paging = Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 5),
                pagingSourceFactory = { pagingSource }
            ).flow
        )
    )

package com.example.rickandmorty.presentaion.home

import androidx.paging.PagingData
import com.example.mvi.common.ModelStoreImpl
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject


@ViewModelScoped
open class HomeModelStore @Inject constructor() :
    ModelStoreImpl<HomeState, HomeState.HomeSideEffect>(
        HomeState(
            state = HomeState.State.INITIAL(),
            paging = PagingData.empty(),
            name = "",
            count = 0,
            firstResult = null,
            secondResult = null
        )
    )

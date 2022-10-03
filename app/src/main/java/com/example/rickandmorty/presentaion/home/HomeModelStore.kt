package com.example.rickandmorty.presentaion.home

import com.example.rickandmorty.common.ModelStoreImpl
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject


@ViewModelScoped
class HomeModelStore @Inject constructor() : ModelStoreImpl<HomeState>(
    HomeState(
        state = HomeState.State.IDEL(),
        paging = null
    )
)

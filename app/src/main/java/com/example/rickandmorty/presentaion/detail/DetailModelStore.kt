package com.example.rickandmorty.presentaion.detail

import com.example.mvi.common.ModelStoreImpl
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject


@ViewModelScoped
open class DetailModelStore @Inject constructor() : ModelStoreImpl<DetailState, DetailSideEffect>(
    DetailState(
        character = null
    )
)

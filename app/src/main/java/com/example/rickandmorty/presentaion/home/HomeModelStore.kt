package com.example.rickandmorty.presentaion.home

import com.example.myupvote.common.ModelStore
import com.example.rickandmorty.common.ModelStoreImpl
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class HomeModelStore @Inject constructor() : ModelStoreImpl<HomeModel>(HomeModel(loading = true, characters = emptyList(), throwable = null))

package com.example.rickandmorty.presentaion.home

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow


data class HomeState(
    val state: State,
    val paging: Flow<PagingData<Character>>
) {
    sealed class State {
        data class NAVIGATE(val character: Character) : HomeState.State()
        object LOADING : State()
        object LOADED : State()
        object FAILED : State()
    }
}



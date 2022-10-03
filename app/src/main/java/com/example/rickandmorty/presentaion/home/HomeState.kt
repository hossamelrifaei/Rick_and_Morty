package com.example.rickandmorty.presentaion.home

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow


data class HomeState(
    val state: State,
    val paging: Flow<PagingData<Character>>?
) {
    sealed class State {
        class NAVIGATE(val character: Character) : State()
        class RETRY : State()
        class IDEL : State()
    }
}



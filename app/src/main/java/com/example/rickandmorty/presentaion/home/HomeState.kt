package com.example.rickandmorty.presentaion.home

import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineScope
import model.Character


data class HomeState(
    val state: State,
    val paging: PagingData<Character>
) {
    sealed class State {
        class INITIAL : HomeState.State()
        class NAVIGATE(val character: Character) : State()
        class RETRY : State()
        class IDEL(val scope: CoroutineScope? = null) : State()
    }
}



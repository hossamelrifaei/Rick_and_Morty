package com.example.rickandmorty.presentaion.home

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow


data class HomeState(
    val state: State,
    val paging: Flow<PagingData<Character>>
) {
    sealed class State {
        data class NAVIGATE(val character: Character,val count:Int) : State()
        data class RETRY(val count:Int) : State()
        object IDEL : State()
    }
}



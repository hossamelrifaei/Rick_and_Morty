package com.example.rickandmorty.presentaion.home

import androidx.paging.PagingData
import com.example.data.remoteresponse.CharactersResponse
import com.example.domain.Resource
import model.Character


data class HomeState(
    val state: State,
    val paging: PagingData<Character>,
    val name: String,
    val count: Int,
    val firstResult: Resource<CharactersResponse>?,
    val secondResult: Resource<CharactersResponse>?
) {
    sealed class State {
        class INITIAL : HomeState.State()
        object RETRY : State()
        data class IDEL(val count: Int) : State()
        class LOADING : HomeState.State()
    }

    sealed class HomeSideEffect {
        class NAVIGATE(val character: Character) : HomeSideEffect()
        object SIDEEFFECT1 : HomeSideEffect()
        object SIDEEFFECT2 : HomeSideEffect()
        object SIDEEFFECT3 : HomeSideEffect()
    }
}



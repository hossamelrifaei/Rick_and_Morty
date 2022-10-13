package com.example.rickandmorty.presentaion.home

import kotlinx.coroutines.CoroutineScope
import model.Character

sealed class HomeViewEvents {
    object INITIAL : HomeViewEvents()
    class RETRY : HomeViewEvents()
    data class LOAD(val scope: CoroutineScope) : HomeViewEvents()
     class OnCharacterSelected(val character: Character) : HomeViewEvents()
}

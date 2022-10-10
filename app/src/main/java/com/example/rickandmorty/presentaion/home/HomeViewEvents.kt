package com.example.rickandmorty.presentaion.home

import kotlinx.coroutines.CoroutineScope
import model.Character

sealed class HomeViewEvents {
    object RETRY : HomeViewEvents()
    data class START(val scope: CoroutineScope) : HomeViewEvents()
    data class OnCharacterSelected(val character: Character) : HomeViewEvents()
}

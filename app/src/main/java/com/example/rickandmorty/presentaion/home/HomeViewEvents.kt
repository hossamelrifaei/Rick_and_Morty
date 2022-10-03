package com.example.rickandmorty.presentaion.home

sealed class HomeViewEvents {
    object RETRY:HomeViewEvents()
    data class OnCharacterSelected(val character: Character) : HomeViewEvents()
}

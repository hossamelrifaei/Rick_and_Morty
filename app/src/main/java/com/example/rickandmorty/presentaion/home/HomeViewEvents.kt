package com.example.rickandmorty.presentaion.home

sealed class HomeViewEvents {
    data class OnCharacterSelected(val character: Character) : HomeViewEvents()
}

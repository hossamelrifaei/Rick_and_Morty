package com.example.rickandmorty.presentaion.home

import model.Character

sealed class HomeViewEvents {
    class RETRY : HomeViewEvents()
    class LOAD : HomeViewEvents()
    class OnCharacterSelected(val character: Character) : HomeViewEvents()
}

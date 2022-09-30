package com.example.rickandmorty.presentaion.home

sealed class HomeViewEvents {
    object OnViewStart : HomeViewEvents()
    object OnViewReady : HomeViewEvents()
}

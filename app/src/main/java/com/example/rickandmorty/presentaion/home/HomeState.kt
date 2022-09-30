package com.example.rickandmorty.presentaion.home


data class HomeModel(
    val loading:Boolean,
    val characters: List<Character>,
    val throwable: Throwable?,
)

//sealed class HomeState{
//    object
//}



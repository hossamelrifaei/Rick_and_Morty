package com.example.rickandmorty.presentaion.home

import com.example.example.Info
import com.example.example.Location
import com.example.example.Origin
import com.example.example.Results

data class CharactersModel( val info: Info?, val characters: List<Character>)

data class Character(
    val id: Int?,
    val name: String?,
    val status: String?,
    val species: String?,
    val type: String?,
    val gender: String?,
    val origin: Origin?,
    val location: Location?,
    val image: String?,
    val episode: ArrayList<String>,
    val url: String?,
    val created: String?
)
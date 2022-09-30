package com.example.rickandmorty.data.api

import com.example.rickandmorty.data.remoteresponse.CharactersResponse
import retrofit2.http.GET

interface RickAndMortyApi {
    @GET("/api/character")
    suspend fun getAllCharacters() : CharactersResponse
}
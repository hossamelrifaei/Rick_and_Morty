package com.example.rickandmorty.data.api

import com.example.rickandmorty.data.remoteresponse.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("/api/character")
    suspend fun getAllCharacters(@Query("page") page: Int): CharactersResponse
}
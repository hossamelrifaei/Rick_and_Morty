package com.example.data.api

import com.example.data.remoteresponse.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("/api/character")
    suspend fun getAllCharacters(@Query("page") page: Int): CharactersResponse
}
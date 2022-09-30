package com.example.rickandmorty.domain.repo

import com.example.rickandmorty.data.remoteresponse.CharactersResponse

interface RickAndMortyRepository {

    suspend fun getCharacters(): CharactersResponse

}
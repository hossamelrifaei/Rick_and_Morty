package com.example.rickandmorty.domain.repo

import com.example.rickandmorty.data.remoteresponse.CharactersResponse
import kotlinx.coroutines.flow.Flow

interface RickAndMortyRepository {

    suspend fun getCharacters(page: Int): Flow<Result<CharactersResponse>>

}
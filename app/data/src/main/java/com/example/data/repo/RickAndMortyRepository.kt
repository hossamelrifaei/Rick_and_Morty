package com.example.data.repo

import com.example.data.remoteresponse.CharactersResponse
import kotlinx.coroutines.flow.Flow

interface RickAndMortyRepository {

    suspend fun getCharacters(page: Int): Flow<Result<CharactersResponse>>

}
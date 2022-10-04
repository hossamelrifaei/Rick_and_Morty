package com.example.data.repo

import com.example.data.api.RickAndMortyApi
import com.example.data.remoteresponse.CharactersResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject


class RickAndMortyRepositoryImpl @Inject constructor(val api: RickAndMortyApi) :
    RickAndMortyRepository {
    override suspend fun getCharacters(page: Int): Flow<Result<CharactersResponse>> =
        flowOf(runCatching { api.getAllCharacters(page) })
}


package com.example.rickandmorty.data.repo

import com.example.rickandmorty.data.api.RickAndMortyApi
import com.example.rickandmorty.data.remoteresponse.CharactersResponse
import com.example.rickandmorty.domain.repo.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject


class RickAndMortyRepositoryImpl @Inject constructor(val api: RickAndMortyApi) :
    RickAndMortyRepository {
    override suspend fun getCharacters(page: Int): Flow<Result<CharactersResponse>> =
        flowOf(runCatching { api.getAllCharacters(page) })
}


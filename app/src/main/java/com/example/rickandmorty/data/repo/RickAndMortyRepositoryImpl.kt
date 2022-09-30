package com.example.rickandmorty.data.repo

import com.example.rickandmorty.data.api.RickAndMortyApi
import com.example.rickandmorty.data.remoteresponse.CharactersResponse
import com.example.rickandmorty.domain.repo.RickAndMortyRepository
import javax.inject.Inject


class RickAndMortyRepositoryImpl @Inject constructor(val api: RickAndMortyApi) : RickAndMortyRepository {
    override suspend fun getCharacters(): CharactersResponse {
        return api.getAllCharacters()
    }

}

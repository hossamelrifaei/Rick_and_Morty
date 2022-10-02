package com.example.rickandmorty.domain.repo.usecases

import com.example.rickandmorty.data.api.RickAndMortyApi
import com.example.rickandmorty.data.remoteresponse.CharactersResponse
import javax.inject.Inject

class GetCharactersUsecase @Inject constructor(val rickAndMortyApi: RickAndMortyApi) {

    suspend operator fun invoke(page: Int): CharactersResponse {
        return rickAndMortyApi.getAllCharacters(page)
    }
}
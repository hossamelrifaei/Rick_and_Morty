package com.example.domain

import com.example.data.api.RickAndMortyApi
import com.example.data.remoteresponse.CharactersResponse
import javax.inject.Inject

class GetCharactersUsecase @Inject constructor(val rickAndMortyApi: RickAndMortyApi) {

    suspend operator fun invoke(page: Int): CharactersResponse {
        return rickAndMortyApi.getAllCharacters(page)
    }
}
package com.example.domain

import com.example.data.api.RickAndMortyApi
import com.example.data.remoteresponse.CharactersResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharactersUsecaseFlow @Inject constructor(val rickAndMortyApi: RickAndMortyApi) {

    suspend operator fun invoke(page: Int): Flow<Resource<CharactersResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = rickAndMortyApi.getAllCharacters(page)
            emit(Resource.Success(response))
        } catch (e: IOException) {
            emit(Resource.Error(""))
        } catch (e: HttpException) {
            emit(Resource.Error(""))
        }

    }

}
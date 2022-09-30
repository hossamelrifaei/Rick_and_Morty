package com.example.rickandmorty.domain.repo.usecases

import com.example.rickandmorty.domain.repo.RickAndMortyRepository
import com.example.rickandmorty.presentaion.home.CharactersModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharactersUsecase @Inject constructor(val repository: RickAndMortyRepository) {

    operator fun invoke(): Flow<Result<CharactersModel>> = flow {
        try {
            val result= repository.getCharacters()
            emit(Result.success(result.toModel()))
        }catch (e:HttpException){
            emit(Result.failure(e))
        }catch (e:IOException){
            emit(Result.failure(e))
        }
    }
}
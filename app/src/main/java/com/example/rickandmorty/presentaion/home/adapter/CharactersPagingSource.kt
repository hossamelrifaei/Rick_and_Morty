package com.example.rickandmorty.presentaion.home.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.domain.repo.usecases.GetCharactersUsecase
import com.example.rickandmorty.presentaion.home.Character
import dagger.hilt.android.scopes.ViewModelScoped
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@ViewModelScoped
open class CharactersPagingSource @Inject constructor(val charactersUsecase: GetCharactersUsecase) :
    PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val response = charactersUsecase(params.key ?: 1)

            val next = (params.key ?: 1)
            LoadResult.Page(
                data = response.toModel().characters,
                prevKey = if (next == 1) null else params.key,
                nextKey = if (next == response.toModel().info.pages) null else next + 1
            )


        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}
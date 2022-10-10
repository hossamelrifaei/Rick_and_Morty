package com.example.rickandmorty.presentaion.home.adapter

import androidx.paging.PagingData
import model.Character

interface PagingInterface {
    fun doRetry()
    suspend fun doSubmitData(data: PagingData<Character>)
}

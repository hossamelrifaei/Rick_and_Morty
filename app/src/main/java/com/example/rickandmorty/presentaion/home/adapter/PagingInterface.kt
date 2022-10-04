package com.example.rickandmorty.presentaion.home.adapter

import androidx.paging.PagingData
import androidx.recyclerview.widget.ConcatAdapter
import com.example.rickandmorty.presentaion.home.Character

interface PagingInterface {
    fun doRetry()
    suspend fun doSubmitData(data: PagingData<Character>)
    fun addLoadingAdapter(): ConcatAdapter
}

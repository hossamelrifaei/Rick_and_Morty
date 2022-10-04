package com.example.rickandmorty.presentaion.home.adapter

import androidx.paging.PagingData
import androidx.recyclerview.widget.ConcatAdapter
import model.Character

interface PagingInterface {
    fun doRetry()
    suspend fun doSubmitData(data: PagingData<Character>)
    fun addLoadingAdapter(): ConcatAdapter
}

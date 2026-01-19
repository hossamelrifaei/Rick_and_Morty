package com.example.rickandmorty.presentaion.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.mvi.common.BaseViewModel
import com.example.rickandmorty.presentaion.home.HomeState
import com.example.rickandmorty.presentaion.home.HomeViewEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
      factory: DetailIntentFactory,
    private val state: SavedStateHandle
) : BaseViewModel<DetailViewEvents, DetailState, DetailSideEffect>(factory) {

    init {
        Log.d("DETAIL", factory.toString())
    }

    fun process(event: DetailViewEvents) {
        factory.process(event)
    }

    override fun onCleared() {
        factory.close()
        super.onCleared()
    }

}
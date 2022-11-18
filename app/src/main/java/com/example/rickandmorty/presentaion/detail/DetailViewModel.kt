package com.example.rickandmorty.presentaion.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val factory: DetailIntentFactory,
    private val state: SavedStateHandle
) : ViewModel() {

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
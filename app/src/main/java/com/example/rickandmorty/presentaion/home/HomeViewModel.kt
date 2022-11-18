package com.example.rickandmorty.presentaion.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val factory: HomeViewIntentFactory,
) : ViewModel() {


    init {
        factory.process(HomeViewEvents.LOAD(viewModelScope))


        factory.subscribe({}, {

            it.onEach {
                Log.d("effect", it.toString())
            }.launchIn(viewModelScope)


        })

    }

    fun process(event: HomeViewEvents) {
        factory.process(event)
    }


    public override fun onCleared() {
        factory.close()
        super.onCleared()
    }


}





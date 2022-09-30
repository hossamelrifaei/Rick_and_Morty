package com.example.rickandmorty.presentaion.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.common.StateSubscriber
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val factory: HomeViewIntentFactory
) : ViewModel(),StateSubscriber<HomeModel> {

    init {
        factory.modelState().subscribeToState().launchIn(viewModelScope)
    }

    fun process(event: HomeViewEvents) {
        factory.process(event)
    }

     override fun Flow<HomeModel>.subscribeToState()= onEach { model ->
        model.characters
    }

    override fun onCleared() {
        super.onCleared()
        factory.close()
    }
}



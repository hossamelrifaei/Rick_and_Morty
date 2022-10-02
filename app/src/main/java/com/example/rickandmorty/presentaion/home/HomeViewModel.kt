package com.example.rickandmorty.presentaion.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.common.StateSubscriber
import com.example.rickandmorty.presentaion.home.adapter.CharacterPagingAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val factory: HomeViewIntentFactory,
    val adapter: CharacterPagingAdapter,
) : ViewModel(), StateSubscriber<HomeState> {

    init {
        factory.modelState(cacheIn = viewModelScope).subscribeToState().launchIn(viewModelScope)
    }

    fun process(event: HomeViewEvents) {
        factory.process(event)
    }

    override fun Flow<HomeState>.subscribeToState() = onEach { model ->
        model.paging.collect {
            adapter.submitData(it)
        }
        when (model.state) {
            HomeState.State.FAILED -> model.state
            HomeState.State.LOADED -> model.state
            HomeState.State.LOADING -> model.state
            is HomeState.State.NAVIGATE -> model.state.character
        }
    }

    override fun onCleared() {
        super.onCleared()
        factory.close()
    }
}



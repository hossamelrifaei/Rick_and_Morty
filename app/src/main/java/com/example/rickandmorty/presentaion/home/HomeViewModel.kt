package com.example.rickandmorty.presentaion.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.recyclerview.widget.ConcatAdapter
import com.example.rickandmorty.common.StateSubscriber
import com.example.rickandmorty.presentaion.home.adapter.CharacterPagingAdapter
import com.example.rickandmorty.presentaion.home.adapter.CharactersLoadingStateAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val factory: HomeViewIntentFactory,
    private val adapter: CharacterPagingAdapter,
    private val loadingAdapter: CharactersLoadingStateAdapter
) : ViewModel(), StateSubscriber<HomeState> {
    val concatAdapter: ConcatAdapter = adapter.withLoadStateFooter(loadingAdapter)

    init {
        factory.modelState().subscribeToState().launchIn(viewModelScope)
    }

    fun process(event: HomeViewEvents) {
        factory.process(event)
    }

    override fun Flow<HomeState>.subscribeToState() = onEach { model ->


        Log.d("STATE",model.state.toString())
        when (model.state) {
            is HomeState.State.NAVIGATE -> Log.d("EVENT", model.state.character.name)
            is HomeState.State.RETRY -> adapter.retry()
            is HomeState.State.IDEL -> viewModelScope.launch {
                model.paging?.cachedIn(this)?.collect {
                    adapter.submitData(it)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        factory.close()
    }
}



package com.example.rickandmorty.presentaion.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.recyclerview.widget.ConcatAdapter
import com.example.rickandmorty.common.StateSubscriber
import com.example.rickandmorty.presentaion.home.adapter.CharacterPagingAdapter
import com.example.rickandmorty.presentaion.home.adapter.CharactersLoadingStateAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val factory: HomeViewIntentFactory,
    private val adapter: CharacterPagingAdapter,
    private val loadingAdapter: CharactersLoadingStateAdapter
) : ViewModel(), StateSubscriber<HomeState> {
    private val _concatAdapter: MutableLiveData<ConcatAdapter> = MutableLiveData<ConcatAdapter>()
    val concatAdapter: LiveData<ConcatAdapter> = _concatAdapter

    init {
        factory.modelState().subscribeToState().launchIn(viewModelScope)
        factory.process(HomeViewEvents.START)
        _concatAdapter.value = adapter.withLoadStateFooter(loadingAdapter)
    }

    fun process(event: HomeViewEvents) {
        factory.process(event)
    }

    override fun Flow<HomeState>.subscribeToState() = onEach { model ->
        //todo filtering
        when (model.state) {
            is HomeState.State.NAVIGATE -> {} //todo character detail screen
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



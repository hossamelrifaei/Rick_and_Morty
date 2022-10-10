package com.example.rickandmorty.presentaion.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvi.common.StateSubscriber
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val factory: HomeViewIntentFactory,

    ) : ViewModel(), StateSubscriber<HomeState> {


    private val _state: MutableLiveData<HomeState> = MutableLiveData<HomeState>()
    val state: LiveData<HomeState> = _state


    init {
        factory.modelState().subscribeToState().launchIn(viewModelScope)
        factory.process(HomeViewEvents.START(viewModelScope))
    }

    fun process(event: HomeViewEvents) {
        factory.process(event)
    }

    override fun Flow<HomeState>.subscribeToState() = onEach { model ->
        _state.postValue(model)
    }


    public override fun onCleared() {
        super.onCleared()
        factory.close()
    }
}



package com.example.rickandmorty.presentaion.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.mvi.common.Intent
import com.example.mvi.common.IntentFactory
import com.example.mvi.common.asyncIntent
import com.example.mvi.common.intent
import com.example.rickandmorty.presentaion.home.adapter.CharactersPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import model.Character
import javax.inject.Inject


@HiltViewModel
open class HomeViewIntentFactory @Inject constructor(
    modelStore: HomeModelStore,
    private val pagingSource: CharactersPagingSource
) : IntentFactory<HomeViewEvents, HomeState, HomeState.HomeSideEffect>(modelStore) {

    override fun toIntent(event: HomeViewEvents): Intent<HomeState> {
        return when (event) {
            is HomeViewEvents.OnCharacterSelected -> openCharacterDetail(event.character)
            is HomeViewEvents.RETRY -> retryIntent()
            is HomeViewEvents.LOAD -> startIntent()
        }
    }

    private fun startIntent(): Intent<HomeState> =
        asyncIntent {

            viewModelScope.launch {
                Pager(config = PagingConfig(pageSize = 20, enablePlaceholders = false),
                    pagingSourceFactory = { pagingSource }
                ).flow.cachedIn(this).collectLatest {
                    chainedIntent { copy(state = HomeState.State.IDEL(count + 1), paging = it) }
                }
            }
        }

    private fun retryIntent(): Intent<HomeState> {
        return intent {
            copy(state = HomeState.State.RETRY)
        }
    }

    fun openCharacterDetail(character: Character):
        Intent<HomeState> {
        sideEffect(HomeState.HomeSideEffect.SIDEEFFECT1)
        sideEffect(HomeState.HomeSideEffect.SIDEEFFECT2)
        sideEffect(HomeState.HomeSideEffect.SIDEEFFECT3)
        return intent { copy() }
    }
}

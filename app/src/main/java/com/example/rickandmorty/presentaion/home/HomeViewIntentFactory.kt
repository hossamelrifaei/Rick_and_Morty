package com.example.rickandmorty.presentaion.home

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.mvi.common.Intent
import com.example.mvi.common.IntentFactory
import com.example.mvi.common.intent
import com.example.mvi.common.sideEffect
import com.example.rickandmorty.presentaion.home.adapter.CharactersPagingSource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import model.Character
import javax.inject.Inject


@ViewModelScoped
open class HomeViewIntentFactory @Inject constructor(
    private val modelStore: HomeModelStore,
    private val pagingSource: CharactersPagingSource
) : IntentFactory<HomeViewEvents, Flow<HomeState>> {
    override fun process(viewEvent: HomeViewEvents) {
        modelStore.process(toIntent(viewEvent))
    }

    private fun toIntent(viewEvent: HomeViewEvents): Intent<HomeState> {
        return when (viewEvent) {
            is HomeViewEvents.OnCharacterSelected -> OpenCharacterDeatil(viewEvent.character)
            is HomeViewEvents.RETRY -> RetryIntent()
            is HomeViewEvents.LOAD -> viewEvent.scope.startIntent()
            HomeViewEvents.INITIAL -> nothingIntent()
        }
    }

    private fun nothingIntent(): Intent<HomeState> = sideEffect {

    }


    private fun CoroutineScope.startIntent(): Intent<HomeState> = sideEffect {
        launch(Dispatchers.IO) {
            Pager(config = PagingConfig(pageSize = 20, enablePlaceholders = false),
                pagingSourceFactory = { pagingSource }
            ).flow.cachedIn(this).collectLatest {
                modelStore.process(intent { copy(state = HomeState.State.IDEL(), paging = it) })
            }
        }
    }


    private fun RetryIntent(): Intent<HomeState> {
        return intent {
            copy(state = HomeState.State.RETRY)
        }
    }

    private fun OpenCharacterDeatil(character: Character): Intent<HomeState> {
        return intent {
            copy(
                state = HomeState.State.NAVIGATE(character)
            )
        }
    }


    private fun chainedIntent(block: HomeState.() -> HomeState) =
        modelStore.process(intent(block))

    private fun onSuccess(results: List<Character>) = chainedIntent {
        copy()
    }

    private fun onError(throwable: Throwable) = chainedIntent {
        copy()
    }


    override fun modelState(): Flow<HomeState> {
        return modelStore.modelState()
    }

    override fun close() {
        modelStore.close()
    }
}

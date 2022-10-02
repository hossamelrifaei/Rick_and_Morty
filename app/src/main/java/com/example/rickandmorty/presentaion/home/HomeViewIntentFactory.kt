package com.example.rickandmorty.presentaion.home

import androidx.paging.cachedIn
import com.example.rickandmorty.common.Intent
import com.example.rickandmorty.common.IntentFactory
import com.example.rickandmorty.common.intent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@ViewModelScoped
class HomeViewIntentFactory @Inject constructor(
    private val modelStore: HomeModelStore
) : IntentFactory<HomeViewEvents, Flow<HomeState>> {
    override fun process(viewEvent: HomeViewEvents) {
        modelStore.process(toIntent(viewEvent))
    }

    private fun toIntent(viewEvent: HomeViewEvents): Intent<HomeState> {
        return when (viewEvent) {
            is HomeViewEvents.OnCharacterSelected -> OpenCharacterDeatil(viewEvent.character)
        }
    }

    private fun OpenCharacterDeatil(character: Character): Intent<HomeState> {
        return intent {
            copy(state = HomeState.State.NAVIGATE(character))
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


    override fun modelState(cacheIn: CoroutineScope?): Flow<HomeState> {
        cacheIn?.launch {
            modelStore.modelState().collect {
                it.paging.cachedIn(this)
            }
        }
        return modelStore.modelState()
    }

    override fun close() {
        modelStore.close()
    }
}

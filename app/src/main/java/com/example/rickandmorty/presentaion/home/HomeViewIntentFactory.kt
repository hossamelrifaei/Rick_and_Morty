package com.example.rickandmorty.presentaion.home

import com.example.rickandmorty.common.Intent
import com.example.rickandmorty.common.IntentFactory
import com.example.rickandmorty.common.intent
import com.example.rickandmorty.domain.repo.usecases.GetCharactersUsecase
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewIntentFactory @Inject constructor(
    private val modelStore: HomeModelStore,
    private val getCharactersUsecase: GetCharactersUsecase
) :
    IntentFactory<HomeViewEvents, Flow<HomeModel>> {
    override fun process(viewEvent: HomeViewEvents) {
        modelStore.process(toIntent(viewEvent))
    }

    private fun toIntent(viewEvent: HomeViewEvents): Intent<HomeModel> {
        return when (viewEvent) {
            HomeViewEvents.OnViewStart -> startLoading()
            HomeViewEvents.OnViewReady -> loadCharacters()
        }
    }

    private fun startLoading(): Intent<HomeModel> {
        return intent {
            copy(loading = true)
        }
    }

    private fun chainedIntent(block: HomeModel.() -> HomeModel) =
        modelStore.process(intent(block))

    fun onSuccess(results: List<Character>) = chainedIntent {
        copy(characters = results)
    }

    fun onError(throwable: Throwable) = chainedIntent {
        copy(throwable = throwable)
    }
    private fun loadCharacters(): Intent<HomeModel> {
        return intent {

            CoroutineScope(Dispatchers.IO).launch {
                getCharactersUsecase().collect {
                    it.onSuccess {
                        onSuccess(it.characters)
                    }.onFailure {
                        onError(it)
                    }
                }
            }

            copy()
        }
    }


    override fun modelState(): Flow<HomeModel> {
        return modelStore.modelState()
    }

    override fun close() {
       modelStore.close()
    }
}

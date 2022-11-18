package com.example.rickandmorty.presentaion.detail

import com.example.mvi.common.Intent
import com.example.mvi.common.IntentFactory
import com.example.mvi.common.intent
import javax.inject.Inject

class DetailIntentFactory @Inject constructor(
    private val modelStore: DetailModelStore
) : IntentFactory<DetailViewEvents, DetailState, DetailSideEffect>(modelStore) {
    override fun toIntent(viewEvent: DetailViewEvents): Intent<DetailState> {
        return intent { copy() }
    }


}

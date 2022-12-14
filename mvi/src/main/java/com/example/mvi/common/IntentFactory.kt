package com.example.mvi.common

import kotlinx.coroutines.flow.Flow

abstract class IntentFactory<EVENT, STATE, SIDE_EFFECT>(
    private val store: ModelStore<STATE, SIDE_EFFECT>
) {

    abstract fun toIntent(event: EVENT): Intent<STATE>

    fun process(event: EVENT) = store.process(toIntent(event))

     fun chainedIntent(block: STATE.() -> STATE) = store.process(intent(block))

    fun sideEffect(side: SIDE_EFFECT) = store.processSideEffect(side)

    fun close() = store.close()

    fun subscribe(state: (Flow<STATE>) -> Unit, sideEffect: (Flow<SIDE_EFFECT>) -> Unit) =
        store.subscribe(state, sideEffect)
}
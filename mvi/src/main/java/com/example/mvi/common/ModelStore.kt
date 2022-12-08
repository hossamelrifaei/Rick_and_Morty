package com.example.mvi.common

import kotlinx.coroutines.flow.Flow

interface ModelStore<STATE, SIDE_EFFECT> {
    fun process(intent: Intent<STATE>)
    fun close()
    fun subscribeState(state: (Flow<STATE>) -> Unit): ModelStoreImpl<STATE, SIDE_EFFECT>
    fun subscribeSideEffect(sideEffect: (Flow<SIDE_EFFECT>) -> Unit)
    fun processSideEffect(sideEffect: SIDE_EFFECT)
}
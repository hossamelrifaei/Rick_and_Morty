package com.example.mvi.common

import kotlinx.coroutines.flow.Flow

interface ModelStore<STATE, SIDE_EFFECT> {
    fun process(intent: Intent<STATE>)
    fun close()
    fun subscribe(state: (Flow<STATE>) -> Unit, sideEffect: (Flow<SIDE_EFFECT>) -> Unit)
    fun processSideEffect(sideEffect: SIDE_EFFECT)
}
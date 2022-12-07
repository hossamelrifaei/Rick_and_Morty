package com.example.mvi.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface MVIView<EVENT, STATE, SIDE_EFFECT> {

    fun CoroutineScope.subscribeToState(state: Flow<STATE>)

    fun CoroutineScope.subscribeToSideEffect(sideEffect: Flow<SIDE_EFFECT>)

    fun viewEvents(): Flow<EVENT>
}
package com.example.mvi.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.Eagerly
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

open class ModelStoreImpl<STATE, SIDE_EFFECT>(startingState: STATE) :
    ModelStore<STATE, SIDE_EFFECT> {
    private var scope = CoroutineScope(Dispatchers.Main)
    private val intentChannel = Channel<Intent<STATE>>(Channel.BUFFERED)
    private val store = MutableStateFlow(startingState)
    private val sideEffectChannel = Channel<SIDE_EFFECT>(Channel.BUFFERED)
    private val sideEffect: SharedFlow<SIDE_EFFECT> =
        sideEffectChannel.receiveAsFlow().shareIn(scope, Eagerly)

    init {
        scope.launch {
            while (isActive)
                store.update { intentChannel.receive().reduce(store.value) }
        }
    }

    override fun process(intent: Intent<STATE>) {
        intentChannel.trySend(intent)
    }

    override fun processSideEffect(sideEffect: SIDE_EFFECT) {
        sideEffectChannel.trySend(sideEffect)
    }

    override fun subscribeState(state: (Flow<STATE>) -> Unit): ModelStoreImpl<STATE, SIDE_EFFECT> {
        state.invoke(store)
        return this
    }

    override fun subscribeSideEffect(sideEffect: (Flow<SIDE_EFFECT>) -> Unit) {
        sideEffect.invoke(this.sideEffect)
    }

    override fun close() {
        intentChannel.close()
        sideEffectChannel.close()
        scope.cancel()
    }
}

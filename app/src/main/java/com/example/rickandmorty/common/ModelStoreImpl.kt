package com.example.rickandmorty.common

import com.example.myupvote.common.ModelStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


open class ModelStoreImpl<S>(startingState: S) : ModelStore<S> {
    private var scope = CoroutineScope(Dispatchers.IO)
    private val intents = Channel<Intent<S>>(capacity = Channel.BUFFERED)
    private val store = MutableStateFlow(startingState)

    init {
        scope.launch {
            while (isActive)
                store.update { intents.receive().reduce(store.value) }
        }
    }

    override fun process(intent: Intent<S>) {
        intents.trySend(intent)
    }

    override fun modelState(): Flow<S> {
        return store
    }

    fun close() {
        scope.cancel()
        intents.close()
    }
}

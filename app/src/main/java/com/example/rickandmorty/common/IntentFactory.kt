package com.example.rickandmorty.common

import kotlinx.coroutines.CoroutineScope

interface IntentFactory<E, S> {
    fun process(viewEvent: E)
    fun modelState(cacheIn: CoroutineScope? = null): S
    fun close()
}
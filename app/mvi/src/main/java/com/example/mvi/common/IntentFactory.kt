package com.example.mvi.common

interface IntentFactory<E, S> {
    fun process(viewEvent: E)
    fun modelState(): S
    fun close()
}
package com.example.rickandmorty.common

import kotlinx.coroutines.flow.Flow

interface ModelStore<S> {
    fun process(intent: Intent<S>)
    fun modelState(): Flow<S>
}
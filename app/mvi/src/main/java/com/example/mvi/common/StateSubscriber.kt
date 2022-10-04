package com.example.mvi.common

import kotlinx.coroutines.flow.Flow

interface StateSubscriber<S> {
    fun Flow<S>.subscribeToState(): Flow<S>
}
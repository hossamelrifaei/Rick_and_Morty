package com.example.rickandmorty.common

import kotlinx.coroutines.flow.Flow

/**
 * Consumers of a given state source often need to create fine-grained subscriptions
 * to control performance and frequency of updates.
 */
interface StateSubscriber<S> {
    fun Flow<S>.subscribeToState(): Flow<S>
}
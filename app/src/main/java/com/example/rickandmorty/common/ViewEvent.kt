package com.example.myupvote.common

import kotlinx.coroutines.flow.Flow

/**
 * This allows us to group all the viewEvents from
 * one view in a single source.
 */
interface ViewEvent<E> {
    fun viewEvents(): Flow<E>
}
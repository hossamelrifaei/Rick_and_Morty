package com.example.rickandmorty.common

interface Intent<T> {
    suspend fun reduce(oldState: T): T
}

fun <T> intent(block: T.() -> T) = BlockIntent(block)

class BlockIntent<T>(val block: T.() -> T) : Intent<T> {
    override suspend fun reduce(oldState: T): T = block(oldState)
}

fun <T> sideEffect(block: T.() -> Unit): Intent<T> = object :
    Intent<T> {
    override suspend fun reduce(oldState: T): T = oldState.apply(block)
}

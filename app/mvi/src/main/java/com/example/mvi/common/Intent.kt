package com.example.mvi.common

interface Intent<STATE> {
    suspend fun reduce(oldState: STATE): STATE
}

class BlockIntent<STATE>(val block: STATE.() -> STATE) : Intent<STATE> {
    override suspend fun reduce(oldState: STATE): STATE = block(oldState)
}

fun <STATE> intent(block: STATE.() -> STATE) = BlockIntent(block)

fun <STATE> asyncIntent(block: STATE.() -> Unit): Intent<STATE> = object :
    Intent<STATE> {
    override suspend fun reduce(oldState: STATE): STATE = oldState.apply(block)
}

fun <STATE> chainedIntent(block: STATE.() -> STATE) = BlockIntent(block)

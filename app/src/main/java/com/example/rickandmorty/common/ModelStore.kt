package com.example.myupvote.common

import com.example.rickandmorty.common.Intent
import kotlinx.coroutines.flow.Flow

interface ModelStore<S> {
    fun process(intent: Intent<S>)
    fun modelState(): Flow<S>
}
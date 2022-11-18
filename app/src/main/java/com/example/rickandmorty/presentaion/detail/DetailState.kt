package com.example.rickandmorty.presentaion.detail

import model.Character

data class DetailState(val character: Character?)
sealed class DetailSideEffect

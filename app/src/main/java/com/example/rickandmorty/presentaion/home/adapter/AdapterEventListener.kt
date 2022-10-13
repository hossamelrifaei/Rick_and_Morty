package com.example.rickandmorty.presentaion.home.adapter

interface AdapterEventListener<T> {
    fun onEvent(event:T)
}
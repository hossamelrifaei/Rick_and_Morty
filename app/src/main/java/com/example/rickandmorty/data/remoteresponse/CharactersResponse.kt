package com.example.rickandmorty.data.remoteresponse

import com.example.example.Info
import com.example.example.Results
import com.example.rickandmorty.presentaion.home.CharactersModel
import com.google.gson.annotations.SerializedName


data class CharactersResponse(
    @SerializedName("info") var info: Info = Info(),
    @SerializedName("results") var results: ArrayList<Results> = arrayListOf()
) {
    fun toModel(): CharactersModel {
        return CharactersModel(
            info = info.toModel(),
            characters = results.map { it.toModel() }.toList()
        )
    }
}
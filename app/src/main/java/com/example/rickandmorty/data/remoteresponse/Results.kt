package com.example.example

import com.example.rickandmorty.presentaion.home.Character
import com.google.gson.annotations.SerializedName
import kotlin.random.Random


data class Results(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("species") var species: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("gender") var gender: String? = null,
    @SerializedName("origin") var origin: Origin? = Origin(),
    @SerializedName("location") var location: Location? = Location(),
    @SerializedName("image") var image: String? = null,
    @SerializedName("episode") var episode: ArrayList<String> = arrayListOf(),
    @SerializedName("url") var url: String? = null,
    @SerializedName("created") var created: String? = null
) {
    fun toModel(): Character {
        return Character(
            id ?: Random.nextInt(10000),
            name ?: "",
            status ?: "",
            species ?: "",
            type ?: "",
            gender ?: "",
            origin?.toModel(),
            location?.toModel(),
            image ?: "",
            episode,
            url ?: "",
            created ?: ""
        )
    }
}
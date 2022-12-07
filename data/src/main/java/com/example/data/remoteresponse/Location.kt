package com.example.example

import model.LocationModel
import com.google.gson.annotations.SerializedName


data class Location(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null
) {
    fun toModel(): LocationModel {
        return LocationModel(name, url)
    }
}

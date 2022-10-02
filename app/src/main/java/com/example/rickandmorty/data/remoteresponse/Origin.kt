package com.example.example

import com.example.rickandmorty.presentaion.home.OriginModel
import com.google.gson.annotations.SerializedName


data class Origin(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null
) {
    fun toModel(): OriginModel {
        return OriginModel(name, url)
    }

}
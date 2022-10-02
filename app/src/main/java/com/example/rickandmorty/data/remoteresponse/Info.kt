package com.example.example

import com.example.rickandmorty.presentaion.home.InfoModel
import com.google.gson.annotations.SerializedName


data class Info(
    @SerializedName("count") var count: Int? = null,
    @SerializedName("pages") var pages: Int? = null,
    @SerializedName("next") var next: String? = null,
    @SerializedName("prev") var prev: String? = null
) {
    fun toModel(): InfoModel {
        return InfoModel(count ?: 0, pages ?: 1)
    }
}
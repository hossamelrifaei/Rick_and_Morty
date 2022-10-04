package com.example.example

import model.OriginModel
import com.google.gson.annotations.SerializedName


data class Origin(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null
) {
    fun toModel(): OriginModel {
        return OriginModel(name, url)
    }

}
package com.example.example

import com.google.gson.annotations.SerializedName


data class Origin(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null
)
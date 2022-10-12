package com.octopus.moviesapp.data.remote.response.dto

import com.google.gson.annotations.SerializedName

data class TrailerDTO(
    @SerializedName("site")
    val site: String?,
    @SerializedName("key")
    val key: String?,
)

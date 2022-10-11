package com.octopus.moviesapp.data.remote.response.dto

import com.google.gson.annotations.SerializedName

data class TrailerDTO(
    @SerializedName("key")
    val key: String?
)

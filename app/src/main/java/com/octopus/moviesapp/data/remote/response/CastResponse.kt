package com.octopus.moviesapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class CastResponse(
    @SerializedName("character")
    val character: String,
    @SerializedName("poster_path")
    val poster_path: String
)
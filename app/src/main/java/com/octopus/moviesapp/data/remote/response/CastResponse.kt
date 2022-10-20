package com.octopus.moviesapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class CastResponse<T>(
    @SerializedName("cast")
    val items: List<T>
)
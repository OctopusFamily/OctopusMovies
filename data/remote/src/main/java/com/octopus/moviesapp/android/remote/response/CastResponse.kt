package com.octopus.moviesapp.android.remote.response

import com.google.gson.annotations.SerializedName

data class CastResponse<T>(
    @SerializedName("cast")
    val items: List<T>
)
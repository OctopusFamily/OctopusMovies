package com.octopus.moviesapp.data.remote.response.dto

import com.google.gson.annotations.SerializedName
import com.octopus.moviesapp.data.remote.response.CastResponse

data class CastDTO(
    @SerializedName("cast")
    val cast: List<CastResponse>,
    )
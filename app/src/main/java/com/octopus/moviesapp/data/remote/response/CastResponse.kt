package com.octopus.moviesapp.data.remote.response

import com.google.gson.annotations.SerializedName
import com.octopus.moviesapp.data.remote.response.dto.CastDTO

data class CastResponse(
    @SerializedName("cast")
    val itemsList: List<CastDTO>
)
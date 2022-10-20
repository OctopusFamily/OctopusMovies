package com.octopus.moviesapp.data.remote.response

import com.google.gson.annotations.SerializedName
import com.octopus.moviesapp.data.remote.response.dto.GenreDTO

data class GenresResponse(
    @SerializedName("genres")
    val items: List<GenreDTO>
)

package com.octopus.moviesapp.android.response

import com.google.gson.annotations.SerializedName
import com.octopus.moviesapp.android.response.dto.GenreDTO

data class GenresResponse(
    @SerializedName("genres")
    val items: List<GenreDTO>
)

package com.octopus.moviesapp.android.remote.response

import com.google.gson.annotations.SerializedName
import com.octopus.moviesapp.android.remote.response.dto.GenreDTO

data class GenresResponse(
    @SerializedName("genres")
    val items: List<GenreDTO>
)

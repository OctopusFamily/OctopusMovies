package com.octopus.moviesapp.android.response.dto

import com.google.gson.annotations.SerializedName

data class TrendingDTO(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("backdrop_path")
    val backDropPath: String?,
    @SerializedName("profile_path")
    val profilePath: String?,
    @SerializedName("media_type")
    val mediaName: String?,
)
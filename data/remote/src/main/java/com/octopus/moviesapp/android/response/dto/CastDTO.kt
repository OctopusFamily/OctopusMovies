package com.octopus.moviesapp.android.response.dto

import com.google.gson.annotations.SerializedName

data class CastDTO(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("original_name")
    val name: String?,
    @SerializedName("profile_path")
    val profile_path: String?,
)
package com.octopus.moviesapp.android.response.dto.account


import com.google.gson.annotations.SerializedName

data class AvatarPath(
    @SerializedName("avatar_path")
    val path: String?,
)
package com.octopus.moviesapp.android.response.dto.account


import com.google.gson.annotations.SerializedName

data class Avatar(
    @SerializedName("tmdb")
    val avatarPath: AvatarPath?,
)
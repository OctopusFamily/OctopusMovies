package com.octopus.moviesapp.data.remote.response.dto.account


import com.google.gson.annotations.SerializedName

data class Avatar(
    @SerializedName("tmdb")
    val avatarPath: AvatarPath?,
)
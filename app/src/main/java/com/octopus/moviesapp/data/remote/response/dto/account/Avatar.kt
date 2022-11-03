package com.octopus.moviesapp.data.remote.response.dto.account


import com.google.gson.annotations.SerializedName
import com.octopus.moviesapp.android.remote.response.dto.account.AvatarPath

data class Avatar(
    @SerializedName("tmdb")
    val avatarPath: AvatarPath?,
)
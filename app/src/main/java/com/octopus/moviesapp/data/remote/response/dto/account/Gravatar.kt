package com.octopus.moviesapp.data.remote.response.dto.account


import com.google.gson.annotations.SerializedName

data class Gravatar(
    @SerializedName("hash")
    val hash: String? = null
)
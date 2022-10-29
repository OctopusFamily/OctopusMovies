package com.octopus.moviesapp.data.remote.response.dto.account


import com.google.gson.annotations.SerializedName

data class AccountDTO(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("avatar")
    val avatar: Avatar?,
)
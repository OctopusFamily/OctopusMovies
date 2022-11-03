package com.octopus.moviesapp.data.remote.response.dto.account


import com.google.gson.annotations.SerializedName
import com.octopus.moviesapp.android.remote.response.dto.account.Avatar

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
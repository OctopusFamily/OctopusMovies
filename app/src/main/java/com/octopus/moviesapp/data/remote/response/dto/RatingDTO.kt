package com.octopus.moviesapp.data.remote.response.dto

import com.google.gson.annotations.SerializedName

data class RatingDTO(
    @SerializedName("status_code")
    val statusCode: Int?,
    @SerializedName("status_message")
    val statusMessage: String?,
    @SerializedName("success")
    val success: Boolean?,
)
package com.octopus.moviesapp.android.response


import com.google.gson.annotations.SerializedName

data class AddMovieToListResponse(
    @SerializedName("status_code")
    val statusCode: Int? = null,
    @SerializedName("status_message")
    val statusMessage: String? = null
)
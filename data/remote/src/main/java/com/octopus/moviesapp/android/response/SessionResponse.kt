package com.octopus.moviesapp.android.response


import com.google.gson.annotations.SerializedName

data class SessionResponse(
    @SerializedName("session_id")
    val sessionId: String?,
    @SerializedName("success")
    val success: Boolean?
)
package com.octopus.moviesapp.android.response

import com.google.gson.annotations.SerializedName

data class CreateListResponse(
    @SerializedName("list_id")
    val listId: Int?,
    @SerializedName("status_code")
    val statusCode: Int?,
    @SerializedName("status_message")
    val statusMessage: String?,
    @SerializedName("success")
    val success: Boolean?
)

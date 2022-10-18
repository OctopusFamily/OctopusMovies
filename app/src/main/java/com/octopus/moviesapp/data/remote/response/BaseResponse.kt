package com.octopus.moviesapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val items: List<T>,
)

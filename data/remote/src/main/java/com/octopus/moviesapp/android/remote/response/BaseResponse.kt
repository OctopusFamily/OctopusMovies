package com.octopus.moviesapp.android.remote.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("results")
    val items: List<T>,
)

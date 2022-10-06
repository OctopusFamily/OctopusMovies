package com.octopus.moviesapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class MultiItemsResponse<T>(
    @SerializedName("results")
    val items: List<T>,
)

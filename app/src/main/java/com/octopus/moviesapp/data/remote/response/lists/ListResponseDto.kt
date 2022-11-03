package com.octopus.moviesapp.data.remote.response.lists


import com.google.gson.annotations.SerializedName
import com.octopus.moviesapp.android.remote.response.lists.ListDetailsDto

data class ListResponseDto<T>(
    @SerializedName("created_by")
    val createdBy: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("favorite_count")
    val favoriteCount: Int?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("iso_639_1")
    val iso6391: String?,
    @SerializedName("item_count")
    val itemCount: Int?,
    @SerializedName("items")
    val items: List<ListDetailsDto>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("poster_path")
    val posterPath: String?
)
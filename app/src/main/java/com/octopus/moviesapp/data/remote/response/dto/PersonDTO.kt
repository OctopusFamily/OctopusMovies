package com.octopus.moviesapp.data.remote.response.dto

import com.google.gson.annotations.SerializedName

data class PersonDTO(
    @SerializedName("imdb_id")
    var imdbId: String?,
    @SerializedName("profile_path")
    var personImageUrl: String?,
    @SerializedName("name")
    var personName: String?,
    @SerializedName("biography")
    var biography: String?,
    @SerializedName("birthday")
    var birthday: String?,
    @SerializedName("known_for_department")
    var career: String?,
    @SerializedName("popularity")
    var popularity: Float?,
    @SerializedName("place_of_birth")
    var placeOfBirth: String?
)

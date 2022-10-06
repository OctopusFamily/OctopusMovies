package com.octopus.moviesapp.data.remote.response.dto

import com.google.gson.annotations.SerializedName

data class EpisodeDTO(
    @SerializedName("air_date")
    val date: String?,
    @SerializedName("crew")
    val crew: List<PersonDTO>?,
    @SerializedName("episode_number")
    val episodeNumber: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("season_number")
    val seasonNumber: Int?,
    @SerializedName("still_path")
    val stillPath: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?,
)
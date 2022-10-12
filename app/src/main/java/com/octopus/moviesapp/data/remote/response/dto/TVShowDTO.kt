package com.octopus.moviesapp.data.remote.response.dto

import com.google.gson.annotations.SerializedName
import com.octopus.moviesapp.domain.model.Genre

data class TVShowDTO(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("backdrop_path")
    val posterImage: String?,
    @SerializedName("first_air_date")
    val started: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("vote_average")
    val voteAverage: Float?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    @SerializedName("episode_count")
    val numberOfEpisode: Int?,
    @SerializedName("season_number")
    val numberOfSeason: Int?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("genres")
    val genres: List<Genre>?,
    @SerializedName("seasons")
    val season: List<SeasonDTO>?,
)

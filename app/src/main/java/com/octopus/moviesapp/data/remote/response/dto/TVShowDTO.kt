package com.octopus.moviesapp.data.remote.response.dto

import com.google.gson.annotations.SerializedName
import com.octopus.moviesapp.domain.model.Genre

data class TVShowDTO(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("poster_path")
    val posterImage: String?,
    @SerializedName("first_air_date")
    val started: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("tagline")
    val tagline: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("vote_average")
    val voteAverage: Float?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    @SerializedName("number_of_episodes")
    val episodesNumber: Int?,
    @SerializedName("number_of_seasons")
    val seasonsNumber: Int?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("genres")
    val genres: List<GenreDTO>?,
    @SerializedName("seasons")
    val seasons: List<SeasonDTO>?,
)

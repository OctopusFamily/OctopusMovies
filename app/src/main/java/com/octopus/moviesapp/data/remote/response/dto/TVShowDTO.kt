package com.octopus.moviesapp.data.remote.response.dto

import com.google.gson.annotations.SerializedName
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.model.Person
import com.octopus.moviesapp.domain.model.Season

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
    @SerializedName("languages")
    val languages: List<String>?,
    @SerializedName("genres")
    val genres: List<Genre>?,
    @SerializedName("seasons")
    val season: List<Season>?,
    @SerializedName("created_by")
    val createdBy: List<Person>?,
)

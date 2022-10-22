package com.octopus.moviesapp.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octopus.moviesapp.util.Constants
import java.util.*

@Entity(tableName = Constants.MOVIE_TABLE)
data class MovieEntity(
    @PrimaryKey
    val _id: Int,
    val movieId: Int,
    val title: String,
    val posterImageUrl: String,
    val voteAverage: Float,
    val releaseDate: String,
)

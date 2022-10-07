package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.data.remote.response.dto.MovieDTO
import com.octopus.moviesapp.domain.model.Movie

class MoviesMapper : Mapper<List<MovieDTO>, List<Movie>> {
    override fun map(input: List<MovieDTO>): List<Movie> {
        val movies = mutableListOf<Movie>()
        input.forEach {
          movies.add(
              Movie(
                  id = it.id ?: 0,
                  title = it.originalTitle ?: "",
                  posterImageUrl = it.posterPath ?: "",
                  voteAverage = it.voteAverage ?: 0f,
                  releaseDate = it.releaseDate ?: "",
              )
          )
        }
        return movies
    }
}
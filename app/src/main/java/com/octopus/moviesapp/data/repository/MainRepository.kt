package com.octopus.moviesapp.data.repository

import com.octopus.moviesapp.data.remote.response.dto.CastDTO
import com.octopus.moviesapp.data.remote.response.dto.TrailerDTO
import com.octopus.moviesapp.domain.enums.GenresType
import com.octopus.moviesapp.domain.enums.MoviesCategory
import com.octopus.moviesapp.domain.enums.TVShowsCategory
import com.octopus.moviesapp.domain.model.*

interface MainRepository {
    suspend fun getMoviesByCategory(moviesCategory: MoviesCategory, page: Int): List<Movie>
    suspend fun getTVShowsByCategory(tvShowCategory: TVShowsCategory, page: Int): List<TVShow>
    suspend fun getGenresByType(genresType: GenresType): List<Genre>

    suspend fun getMovieTrailersById(movieId: Int): List<Trailer>
    suspend fun getMovieCastById(movieId: Int): List<Cast>
}
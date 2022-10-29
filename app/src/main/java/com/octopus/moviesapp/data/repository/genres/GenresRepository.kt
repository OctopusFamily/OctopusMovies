package com.octopus.moviesapp.data.repository.genres

import com.octopus.moviesapp.data.remote.response.dto.GenreDTO
import com.octopus.moviesapp.data.remote.response.dto.MovieDTO
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.domain.types.GenresType

interface GenresRepository {
    suspend fun getGenresByType(genresType: String): List<GenreDTO>
    suspend fun getListOfMoviesByGenresId(genreId: Int): List<MovieDTO>
    suspend fun getListOfTVShowsByGenresId(genreId: Int): List<TVShow>
}
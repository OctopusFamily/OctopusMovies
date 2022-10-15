package com.octopus.moviesapp.data.repository

import com.octopus.moviesapp.domain.types.GenresType
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.TVShow

interface GenresRepository {
    suspend fun getGenresByType(genresType: GenresType): List<Genre>
    suspend fun getListOfMoviesByGenresId(genreId: Int): List<Movie>
    suspend fun getListOfTVShowsByGenresId(genreId: Int): List<TVShow>
}
package com.octopus.moviesapp.repositories.repository.genres

import com.octopus.moviesapp.android.response.dto.GenreDTO
import com.octopus.moviesapp.android.response.dto.MovieDTO
import com.octopus.moviesapp.android.response.dto.TVShowDTO
import com.octopus.moviesapp.repositories.repository.type.GenresType

interface GenresRepository {
    suspend fun getGenresByType(genresType: GenresType): List<GenreDTO>
    suspend fun getListOfMoviesByGenresId(genreId: Int, page: Int): List<MovieDTO>
    suspend fun getListOfTVShowsByGenresId(genreId: Int, page: Int): List<TVShowDTO>
//    fun getGenreTVShowsPagingSource(genreId: Int): GenreTVShowsPagingSource
//    fun getGenreMoviesPagingSource(genreId: Int): GenreMoviesPagingSource
}

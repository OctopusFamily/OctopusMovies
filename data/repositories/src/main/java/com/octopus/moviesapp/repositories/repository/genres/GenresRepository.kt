package com.octopus.moviesapp.repositories.repository.genres

import com.octopus.moviesapp.android.local.types.GenresType
import com.octopus.moviesapp.android.remote.response.dto.GenreDTO
import com.octopus.moviesapp.android.remote.response.dto.MovieDTO
import com.octopus.moviesapp.android.remote.response.dto.TVShowDTO

interface GenresRepository {
    suspend fun getGenresByType(genresType: GenresType): List<GenreDTO>
    suspend fun getListOfMoviesByGenresId(genreId: Int, page: Int): List<MovieDTO>
    suspend fun getListOfTVShowsByGenresId(genreId: Int, page: Int): List<TVShowDTO>
//    fun getGenreTVShowsPagingSource(genreId: Int): GenreTVShowsPagingSource
//    fun getGenreMoviesPagingSource(genreId: Int): GenreMoviesPagingSource
}

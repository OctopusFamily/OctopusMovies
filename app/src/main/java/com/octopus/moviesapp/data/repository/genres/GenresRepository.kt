package com.octopus.moviesapp.data.repository.genres

import com.octopus.moviesapp.data.remote.pagingsource.GenreMoviesPagingSource
import com.octopus.moviesapp.data.remote.pagingsource.GenreTVShowsPagingSource
import com.octopus.moviesapp.data.remote.response.dto.GenreDTO
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.domain.types.GenresType

interface GenresRepository {
    suspend fun getGenresByType(genresType: GenresType): List<GenreDTO>
    suspend fun getListOfMoviesByGenresId(genreId: Int, page: Int): List<Movie>
    suspend fun getListOfTVShowsByGenresId(genreId: Int, page: Int): List<TVShow>
    fun getGenreTVShowsPagingSource(genreId: Int): GenreTVShowsPagingSource
    fun getGenreMoviesPagingSource(genreId: Int): GenreMoviesPagingSource
}

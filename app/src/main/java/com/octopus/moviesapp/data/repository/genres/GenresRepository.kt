package com.octopus.moviesapp.data.repository.genres

import com.octopus.moviesapp.data.remote.pagingsource.MoviesGenrePagingSource
import com.octopus.moviesapp.data.remote.pagingsource.TVShowsGenrePagingSource
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.domain.types.GenresType

interface GenresRepository {
    suspend fun getGenresByType(genresType: GenresType): List<Genre>
    suspend fun getListOfMoviesByGenresId(genreId: Int): List<Movie>
    suspend fun getListOfTVShowsByGenresId(genreId: Int, page: Int): List<TVShow>
    fun getTVShowsByGenreIdPagingSource(genreId: Int): TVShowsGenrePagingSource
    fun getMoviesByGenreIdPagingSource(genreId: Int): MoviesGenrePagingSource
}

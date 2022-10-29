package com.octopus.moviesapp.data.repository.genres

import com.octopus.moviesapp.data.remote.pagingsource.TVShowsGenrePagingSource
import com.octopus.moviesapp.data.remote.pagingsource.TVShowsPagingSource
import com.octopus.moviesapp.data.remote.response.dto.GenreDTO
import com.octopus.moviesapp.domain.types.GenresType
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.domain.types.TVShowsCategory

interface GenresRepository {
    suspend fun getGenresByType(genresType: GenresType): List<GenreDTO>
    suspend fun getListOfMoviesByGenresId(genreId: Int): List<Movie>
    suspend fun getListOfTVShowsByGenresId(genreId: Int, page: Int): List<TVShow>
    fun getTVShowsByGenreIdPagingSource(genreId: Int): TVShowsGenrePagingSource
}
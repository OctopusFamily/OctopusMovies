package com.octopus.moviesapp.domain.repository

import androidx.paging.PagingData
import com.octopus.moviesapp.domain.enums.GenresList
import com.octopus.moviesapp.domain.enums.MoviesCategory
import com.octopus.moviesapp.domain.enums.TVShowsCategory
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.TVShow
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getMoviesByCategory(moviesCategory: MoviesCategory, page: Int): Flow<PagingData<Movie>>
    suspend fun getTVShowsByCategory(tvShowCategory: TVShowsCategory, page: Int): List<TVShow>
    suspend fun getGenresByList(genresByList: GenresList): List<Genre>
}
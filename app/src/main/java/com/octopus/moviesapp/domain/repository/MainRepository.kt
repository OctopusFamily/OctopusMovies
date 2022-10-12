package com.octopus.moviesapp.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.octopus.moviesapp.domain.enums.GenresList
import com.octopus.moviesapp.domain.enums.MoviesCategory
import com.octopus.moviesapp.domain.enums.TVShowsCategory
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.TVShow
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getMoviesByCategory(moviesCategory: MoviesCategory): Flow<PagingData<Movie>>
    suspend fun getTVShowsByCategory(tvShowCategory: TVShowsCategory, page: Int): List<TVShow>
    suspend fun getGenresByList(genresByList: GenresList): List<Genre>
}
package com.octopus.moviesapp.data.repository

import com.octopus.moviesapp.data.remote.request.ApiService
import com.octopus.moviesapp.domain.enums.GenresType
import com.octopus.moviesapp.domain.enums.MoviesCategory
import com.octopus.moviesapp.domain.enums.TVShowsCategory
import com.octopus.moviesapp.domain.mapper.GenresMapper
import com.octopus.moviesapp.domain.mapper.MoviesMapper
import com.octopus.moviesapp.domain.mapper.TVShowMapper
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.TVShow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val moviesMapper: MoviesMapper,
    private val tvShowMapper: TVShowMapper,
    private val genresMapper: GenresMapper,
) : MainRepository {

    override suspend fun getMoviesByCategory(
        moviesCategory: MoviesCategory,
        page: Int,
    ): List<Movie> {
        return moviesMapper.map(
            apiService.getMoviesByCategory(moviesCategory.pathName, page).items
        )
    }

    override suspend fun getTVShowsByCategory(
        tvShowCategory: TVShowsCategory,
        page: Int,
    ): List<TVShow> {
        return tvShowMapper.map(
            apiService.getTVShowsByCategory(tvShowCategory.pathName, page).items
        )
    }

    override suspend fun getGenresByType(
        genresType: GenresType,
    ): List<Genre> {
        return genresMapper.map(
            Pair(apiService.getGenresByType(genresType.pathName).itemsList, genresType)
        )
    }
}
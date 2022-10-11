package com.octopus.moviesapp.data.repository

import com.octopus.moviesapp.data.remote.request.ApiService
import com.octopus.moviesapp.data.remote.response.dto.CastDTO
import com.octopus.moviesapp.data.remote.response.dto.TrailerDTO
import com.octopus.moviesapp.domain.enums.GenresType
import com.octopus.moviesapp.domain.enums.MoviesCategory
import com.octopus.moviesapp.domain.enums.TVShowsCategory
import com.octopus.moviesapp.domain.mapper.*
import com.octopus.moviesapp.domain.model.*
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val moviesMapper: MoviesMapper,
    private val tvShowMapper: TVShowMapper,
    private val genresMapper: GenresMapper,
    private val castMapper:CastMapper,
    private val trailerMapper: TrailerMapper
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

    override suspend fun getMovieTrailersById(movieId: Int): List<Trailer> {
        return trailerMapper.map(apiService.getMovieTrailersById(movieId).items)
    }

    override suspend fun getMovieCastById(movieId: Int): List<Cast> {
        return castMapper.map(apiService.getMovieCastById(movieId).items)
    }
}
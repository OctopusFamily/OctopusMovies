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
    private val trailerMapper: TrailerMapper,
    private val movieDetailsMapper: MovieDetailsMapper,
    private val tvShowDetailsMapper: TVShowDetailsMapper,
) : MainRepository {

    // Movies Resources
    override suspend fun getMoviesByCategory(
        moviesCategory: MoviesCategory,
        page: Int,
    ): List<Movie> {
        return moviesMapper.map(
            apiService.getMoviesByCategory(moviesCategory.pathName, page).items
        )
    }

    override suspend fun getMovieDetailsById(movieId: Int): MovieDetails {
        return movieDetailsMapper.map(apiService.getMovieById(movieId))
    }

    override suspend fun getMovieTrailerById(movieId: Int): Trailer {
        return trailerMapper.map(apiService.getMovieTrailersById(movieId).items)
    }

    override suspend fun getMovieCastById(movieId: Int): List<Cast> {
        return castMapper.map(apiService.getMovieCastById(movieId).items)
    }

    override suspend fun getTVShowDetailsById(tvShowId: Int): TVShowDetails {
       return tvShowDetailsMapper.map(apiService.getTVShowById(tvShowId))
    }

    override suspend fun getTVShowCastById(tvShowId: Int): List<Cast> {
        return castMapper.map(apiService.getTVShowCastById(tvShowId).items)
    }

    override suspend fun getTVShowsTrailersById(tvShowId: Int): Trailer {
        return trailerMapper.map(apiService.getTVShowsTrailersById(tvShowId).items)
    }

    // TV Shows Resources
    override suspend fun getTVShowsByCategory(
        tvShowCategory: TVShowsCategory,
        page: Int,
    ): List<TVShow> {
        return tvShowMapper.map(
            apiService.getTVShowsByCategory(tvShowCategory.pathName, page).items
        )
    }

    // Genres Resources
    override suspend fun getGenresByType(
        genresType: GenresType,
    ): List<Genre> {
        return genresMapper.map(
            Pair(apiService.getGenresByType(genresType.pathName).itemsList, genresType)
        )
    }
}
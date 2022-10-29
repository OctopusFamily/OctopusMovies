package com.octopus.moviesapp.data.repository.movies

import com.octopus.moviesapp.data.remote.pagingsource.MoviesPagingSource
import com.octopus.moviesapp.data.remote.response.dto.MovieDTO
import com.octopus.moviesapp.data.remote.service.TMDBApiService
import com.octopus.moviesapp.domain.mapper.*
import com.octopus.moviesapp.domain.model.*
import com.octopus.moviesapp.domain.types.MoviesCategory
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val tmdbApiService: TMDBApiService,
    private val moviesMapper: MoviesMapper,
    private val movieDetailsMapper: MovieDetailsMapper,
    private val trailerMapper: TrailerMapper,
    private val castMapper: CastMapper,
    private val searchResultMapper: SearchResultMapper,
) : MoviesRepository {
    override suspend fun getMoviesByCategory(
        moviesCategory: MoviesCategory,
        page: Int
    ): List<MovieDTO> {
        return tmdbApiService.getMoviesByCategory(moviesCategory.pathName, page).items
    }

    override suspend fun getMovieDetailsById(movieId: Int): MovieDetails {
        return movieDetailsMapper.map(tmdbApiService.getMovieById(movieId))
    }

    override suspend fun getMovieTrailerById(movieId: Int): Trailer {
        return trailerMapper.map(tmdbApiService.getMovieTrailersById(movieId).items)
    }

    override suspend fun getMovieCastById(movieId: Int): List<Cast> {
        return castMapper.map(tmdbApiService.getMovieCastById(movieId).items)
    }

    override suspend fun getSearchMultiMedia(query: String): List<SearchResult> {
        return searchResultMapper.mapList(tmdbApiService.getSearchMultiMedia(query).items)
    }

    override fun getMoviesPagingSource(moviesCategory: MoviesCategory): MoviesPagingSource {
        return MoviesPagingSource(tmdbApiService, moviesCategory, moviesMapper)
    }


}
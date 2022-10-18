package com.octopus.moviesapp.data.repository

import com.octopus.moviesapp.data.paging.MoviesPagingSource
import com.octopus.moviesapp.data.remote.service.TMDBApiService
import com.octopus.moviesapp.domain.mapper.CastMapper
import com.octopus.moviesapp.domain.mapper.MovieDetailsMapper
import com.octopus.moviesapp.domain.mapper.MoviesMapper
import com.octopus.moviesapp.domain.mapper.TrailerMapper
import com.octopus.moviesapp.domain.model.Cast
import com.octopus.moviesapp.domain.model.MovieDetails
import com.octopus.moviesapp.domain.model.Trailer
import com.octopus.moviesapp.domain.types.MoviesCategory
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val tmdbApiService: TMDBApiService,
    private val moviesMapper: MoviesMapper,
    private val movieDetailsMapper: MovieDetailsMapper,
    private val trailerMapper: TrailerMapper,
    private val castMapper: CastMapper,
) : MoviesRepository {
//    override suspend fun getMoviesByCategory(moviesCategory: MoviesCategory, page: Int): List<Movie> {
//        return moviesMapper.map(tmdbApiService.getMoviesByCategory(moviesCategory.pathName, page).items)
//    }

    override suspend fun getMovieDetailsById(movieId: Int): MovieDetails {
        return movieDetailsMapper.map(tmdbApiService.getMovieById(movieId))
    }

    override suspend fun getMovieTrailerById(movieId: Int): Trailer {
        return trailerMapper.map(tmdbApiService.getMovieTrailersById(movieId).items)
    }

    override suspend fun getMovieCastById(movieId: Int): List<Cast> {
        return castMapper.map(tmdbApiService.getMovieCastById(movieId).itemsList)
    }

    override fun getMovies(moviesCategory: MoviesCategory): MoviesPagingSource {
        return MoviesPagingSource(tmdbApiService, moviesCategory)
    }
}
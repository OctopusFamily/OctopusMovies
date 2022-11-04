package com.octopus.moviesapp.repositories.repository.movies

import com.octopus.moviesapp.android.remote.response.dto.CastDTO
import com.octopus.moviesapp.android.remote.response.dto.MovieDTO
import com.octopus.moviesapp.android.remote.response.dto.TrailerDTO
import com.octopus.moviesapp.android.remote.service.TMDBApiService
import com.octopus.moviesapp.repositories.repository.type.MoviesCategory
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val tmdbApiService: TMDBApiService,
    ) : MoviesRepository {
    override suspend fun getMoviesByCategory(
        moviesCategory: MoviesCategory,
        page: Int
    ): List<MovieDTO> {
        return tmdbApiService.getMoviesByCategory(moviesCategory.pathName, page).items
    }

    override suspend fun getMovieDetailsById(movieId: Int): MovieDTO {
        return tmdbApiService.getMovieById(movieId)
    }

    override suspend fun getMovieTrailerById(movieId: Int): List<TrailerDTO> {
        return tmdbApiService.getMovieTrailersById(movieId).items
    }

    override suspend fun getMovieCastById(movieId: Int): List<CastDTO> {
        return tmdbApiService.getMovieCastById(movieId).items
    }


//
//    override fun getMoviesPagingSource(moviesCategory: MoviesCategory): MoviesPagingSource {
//        return MoviesPagingSource(tmdbApiService, moviesCategory, moviesMapper)
//    }


}
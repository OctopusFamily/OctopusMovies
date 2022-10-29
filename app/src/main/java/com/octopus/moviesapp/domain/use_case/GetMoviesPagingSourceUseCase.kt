package com.octopus.moviesapp.domain.use_case

import com.octopus.moviesapp.data.remote.pagingsource.MoviesPagingSource
import com.octopus.moviesapp.data.repository.movies.MoviesRepository
import com.octopus.moviesapp.domain.types.MoviesCategory
import javax.inject.Inject

class GetMoviesPagingSourceUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    operator fun invoke(moviesCategory: MoviesCategory): MoviesPagingSource {
        return moviesRepository.getMoviesPagingSource(moviesCategory)
    }
}
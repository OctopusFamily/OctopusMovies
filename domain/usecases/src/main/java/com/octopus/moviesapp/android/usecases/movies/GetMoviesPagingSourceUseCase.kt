package com.octopus.moviesapp.android.usecases

import com.octopus.moviesapp.repositories.repository.movies.MoviesRepository
import com.octopus.moviesapp.repositories.repository.type.MoviesCategory
import javax.inject.Inject

class GetMoviesPagingSourceUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    operator fun invoke(moviesCategory: MoviesCategory): MoviesPagingSource {
        return moviesRepository.getMoviesPagingSource(moviesCategory)
    }
}
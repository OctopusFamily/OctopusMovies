package com.octopus.moviesapp.android.usecases

import com.octopus.moviesapp.android.usecases.mapper.MoviesMapper
import com.octopus.moviesapp.models.model.Movie
import com.octopus.moviesapp.repositories.repository.movies.MoviesRepository
import com.octopus.moviesapp.repositories.repository.type.MoviesCategory
import javax.inject.Inject

class FetchMoviesByCategoryUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val moviesMapper: MoviesMapper,
) {
    suspend operator fun invoke(moviesCategory: MoviesCategory, page: Int): List<Movie> {
        return moviesMapper.mapList(moviesRepository.getMoviesByCategory(moviesCategory, page))
    }
}
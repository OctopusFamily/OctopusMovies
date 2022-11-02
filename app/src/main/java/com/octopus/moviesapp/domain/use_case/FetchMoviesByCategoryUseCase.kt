package com.octopus.moviesapp.domain.use_case

import com.octopus.moviesapp.data.repository.movies.MoviesRepository
import com.octopus.moviesapp.domain.mapper.MoviesMapper
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.types.MoviesCategory
import javax.inject.Inject

class FetchMoviesByCategoryUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val moviesMapper: MoviesMapper,
) {
    suspend operator fun invoke(moviesCategory: MoviesCategory, page: Int): List<Movie> {
        return moviesMapper.mapList(moviesRepository.getMoviesByCategory(moviesCategory, page))
    }
}
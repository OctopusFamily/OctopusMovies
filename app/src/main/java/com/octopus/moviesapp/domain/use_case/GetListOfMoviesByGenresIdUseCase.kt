package com.octopus.moviesapp.domain.use_case

import com.octopus.moviesapp.data.remote.service.TMDBApiService
import com.octopus.moviesapp.data.repository.genres.GenresRepository
import com.octopus.moviesapp.domain.mapper.MoviesMapper
import com.octopus.moviesapp.domain.model.Movie
import javax.inject.Inject

class GetListOfMoviesByGenresIdUseCase @Inject constructor(
    private val genresRepository: GenresRepository,
    private val moviesMapper :MoviesMapper,
) {
    suspend operator fun invoke(genreId: Int): List<Movie> {
        return moviesMapper.map(genresRepository.getListOfMoviesByGenresId(genreId))
    }
}
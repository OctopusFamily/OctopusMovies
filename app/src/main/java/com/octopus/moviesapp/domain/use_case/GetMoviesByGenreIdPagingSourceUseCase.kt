package com.octopus.moviesapp.domain.use_case

import com.octopus.moviesapp.data.remote.pagingsource.MoviesGenrePagingSource
import com.octopus.moviesapp.data.repository.genres.GenresRepository
import javax.inject.Inject

class GetMoviesByGenreIdPagingSourceUseCase @Inject constructor(
    private val genresRepository: GenresRepository
) {
    operator fun invoke(genreId: Int): MoviesGenrePagingSource {
        return genresRepository.getMoviesByGenreIdPagingSource(genreId)
    }
}

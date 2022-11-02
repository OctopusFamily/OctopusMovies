package com.octopus.moviesapp.domain.use_case

import com.octopus.moviesapp.data.remote.pagingsource.GenreMoviesPagingSource
import com.octopus.moviesapp.data.repository.genres.GenresRepository
import javax.inject.Inject

class GetGenreMoviesPagingSourceUseCase @Inject constructor(
    private val genresRepository: GenresRepository
) {
    operator fun invoke(genreId: Int): GenreMoviesPagingSource {
        return genresRepository.getGenreMoviesPagingSource(genreId)
    }
}

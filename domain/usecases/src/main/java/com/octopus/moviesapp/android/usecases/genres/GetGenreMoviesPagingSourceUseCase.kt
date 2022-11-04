package com.octopus.moviesapp.android.usecases.genres

import com.octopus.moviesapp.repositories.repository.genres.GenresRepository
import javax.inject.Inject

class GetGenreMoviesPagingSourceUseCase @Inject constructor(
    private val genresRepository: GenresRepository
) {
    operator fun invoke(genreId: Int): GenreMoviesPagingSource {
        return genresRepository.getGenreMoviesPagingSource(genreId)
    }
}

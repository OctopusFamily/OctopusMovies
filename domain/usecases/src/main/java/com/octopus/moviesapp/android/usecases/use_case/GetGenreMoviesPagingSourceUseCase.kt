package com.octopus.moviesapp.android.usecases.use_case

import com.octopus.moviesapp.android.remote.pagingsource.GenreMoviesPagingSource
import com.octopus.moviesapp.data.repository.genres.GenresRepository
import javax.inject.Inject

class GetGenreMoviesPagingSourceUseCase @Inject constructor(
    private val genresRepository: GenresRepository
) {
    operator fun invoke(genreId: Int): GenreMoviesPagingSource {
        return genresRepository.getGenreMoviesPagingSource(genreId)
    }
}

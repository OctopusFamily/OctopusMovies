package com.octopus.moviesapp.android.usecases

import com.octopus.moviesapp.repositories.repository.genres.GenresRepository
import javax.inject.Inject

class GetGenreTVShowsPagingSourceUseCase @Inject constructor(
    private val genresRepository: GenresRepository
) {
    operator fun invoke(genreId: Int): GenreTVShowsPagingSource {
        return genresRepository.getGenreTVShowsPagingSource(genreId)
    }
}

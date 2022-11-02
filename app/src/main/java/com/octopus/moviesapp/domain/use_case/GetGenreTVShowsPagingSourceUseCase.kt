package com.octopus.moviesapp.domain.use_case

import com.octopus.moviesapp.data.remote.pagingsource.GenreTVShowsPagingSource
import com.octopus.moviesapp.data.repository.genres.GenresRepository
import javax.inject.Inject

class GetGenreTVShowsPagingSourceUseCase @Inject constructor(
    private val genresRepository: GenresRepository
) {
    operator fun invoke(genreId: Int): GenreTVShowsPagingSource {
        return genresRepository.getGenreTVShowsPagingSource(genreId)
    }
}

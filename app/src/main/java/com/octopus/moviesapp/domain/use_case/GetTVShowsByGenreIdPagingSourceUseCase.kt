package com.octopus.moviesapp.domain.use_case

import com.octopus.moviesapp.data.remote.pagingsource.TVShowsGenrePagingSource
import com.octopus.moviesapp.data.repository.genres.GenresRepository
import javax.inject.Inject

class GetTVShowsByGenreIdPagingSourceUseCase @Inject constructor(
    private val genresRepository: GenresRepository
) {
    operator fun invoke(genreId: Int): TVShowsGenrePagingSource {
        return genresRepository.getTVShowsByGenreIdPagingSource(genreId)
    }
}

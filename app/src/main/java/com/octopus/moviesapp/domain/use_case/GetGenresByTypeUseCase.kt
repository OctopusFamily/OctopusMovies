package com.octopus.moviesapp.domain.use_case

import com.octopus.moviesapp.data.repository.genres.GenresRepository
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.types.GenresType
import javax.inject.Inject

class GetGenresByTypeUseCase @Inject constructor(
    private val genresRepository: GenresRepository
) {
    suspend operator fun invoke(genresType: GenresType): List<Genre> {
        return genresRepository.getGenresByType(genresType)
    }
}
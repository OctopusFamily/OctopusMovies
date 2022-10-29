package com.octopus.moviesapp.domain.use_case

import com.octopus.moviesapp.data.remote.service.TMDBApiService
import com.octopus.moviesapp.data.repository.genres.GenresRepository
import com.octopus.moviesapp.domain.mapper.GenresMapper
import com.octopus.moviesapp.domain.mapper.GenresMapper_Factory
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.types.GenresType
import javax.inject.Inject

class GetGenresByTypeUseCase @Inject constructor(
    private val genresRepository: GenresRepository,
    private val mapper: GenresMapper,
) {
    suspend operator fun invoke(genresType: GenresType): List<Genre> {
        return mapper.map(Pair(genresRepository.getGenresByType(genresType.pathName), genresType))
    }
}
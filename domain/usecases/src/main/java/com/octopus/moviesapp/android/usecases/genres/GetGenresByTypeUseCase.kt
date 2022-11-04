package com.octopus.moviesapp.android.usecases.usecase.genres


import com.octopus.moviesapp.android.usecases.mapper.GenresMapper
import com.octopus.moviesapp.models.model.Genre
import com.octopus.moviesapp.repositories.repository.genres.GenresRepository
import com.octopus.moviesapp.repositories.repository.type.GenresType
import javax.inject.Inject

class GetGenresByTypeUseCase @Inject constructor(
    private val genresRepository: GenresRepository,
    private val genresMapper: GenresMapper
) {
    suspend operator fun invoke(genresType: GenresType): List<Genre> {
        return genresMapper.map(Pair(genresRepository.getGenresByType(genresType), genresType))
    }
}
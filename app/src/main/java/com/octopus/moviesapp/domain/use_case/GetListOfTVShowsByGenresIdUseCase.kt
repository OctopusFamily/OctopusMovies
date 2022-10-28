package com.octopus.moviesapp.domain.use_case

import com.octopus.moviesapp.data.repository.genres.GenresRepository
import com.octopus.moviesapp.domain.model.TVShow
import javax.inject.Inject

class GetListOfTVShowsByGenresIdUseCase @Inject constructor(
    private val genresRepository: GenresRepository
) {
    suspend operator fun invoke(genreId :Int) :List<TVShow>{
        return genresRepository.getListOfTVShowsByGenresId(genreId)
    }
}
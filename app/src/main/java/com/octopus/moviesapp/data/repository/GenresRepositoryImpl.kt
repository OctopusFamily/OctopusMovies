package com.octopus.moviesapp.data.repository

import com.octopus.moviesapp.data.remote.service.TMDBApiService
import com.octopus.moviesapp.domain.types.GenresType
import com.octopus.moviesapp.domain.mapper.GenresMapper
import com.octopus.moviesapp.domain.mapper.MoviesMapper
import com.octopus.moviesapp.domain.mapper.TVShowMapper
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.TVShow
import javax.inject.Inject

class GenresRepositoryImpl @Inject constructor(
    private val tmdbApiService: TMDBApiService,
    private val moviesMapper: MoviesMapper,
    private val tvShowMapper: TVShowMapper,
    private val genresMapper: GenresMapper,
) : GenresRepository {
    override suspend fun getGenresByType(genresType: GenresType): List<Genre> {
        return genresMapper.map(Pair(tmdbApiService.getGenresByType(genresType.pathName).itemsList, genresType))
    }

    override suspend fun getListOfMoviesByGenresId(genreId: Int): List<Movie> {
        return moviesMapper.map(tmdbApiService.getMoviesByGenresId(genreId).items)
    }

    override suspend fun getListOfTVShowsByGenresId(genreId: Int): List<TVShow> {
        return tvShowMapper.map(tmdbApiService.getTVShowsByGenresId(genreId).items)
    }
}
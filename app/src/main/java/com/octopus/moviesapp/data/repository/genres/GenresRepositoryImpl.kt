package com.octopus.moviesapp.data.repository.genres

import com.octopus.moviesapp.data.remote.pagingsource.MoviesGenrePagingSource
import com.octopus.moviesapp.data.remote.pagingsource.TVShowsGenrePagingSource
import com.octopus.moviesapp.data.remote.service.TMDBApiService
import com.octopus.moviesapp.domain.mapper.GenresMapper
import com.octopus.moviesapp.domain.mapper.MoviesMapper
import com.octopus.moviesapp.domain.mapper.TVShowsMapper
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.domain.types.GenresType
import javax.inject.Inject

class GenresRepositoryImpl @Inject constructor(
    private val tmdbApiService: TMDBApiService,
    private val moviesMapper: MoviesMapper,
    private val tvShowsMapper: TVShowsMapper,
    private val genresMapper: GenresMapper,
) : GenresRepository {
    override suspend fun getGenresByType(genresType: GenresType): List<Genre> {
        return genresMapper.map(Pair(tmdbApiService.getGenresByType(genresType.pathName).items, genresType))
    }

    override suspend fun getListOfMoviesByGenresId(genreId: Int,page: Int): List<Movie> {
        return moviesMapper.mapList(tmdbApiService.getMoviesByGenresId(genreId,page).items)
    }

    override suspend fun getListOfTVShowsByGenresId(genreId: Int, page: Int): List<TVShow> {
        return tvShowsMapper.mapList(tmdbApiService.getTVShowsByGenresId(genreId, page).items)
    }

    override  fun getTVShowsByGenreIdPagingSource(genreId: Int): TVShowsGenrePagingSource {
        return TVShowsGenrePagingSource(tmdbApiService, genreId, tvShowsMapper)
    }

    override fun getMoviesByGenreIdPagingSource(genreId: Int): MoviesGenrePagingSource {
        return MoviesGenrePagingSource(tmdbApiService, genreId, moviesMapper)
    }
}
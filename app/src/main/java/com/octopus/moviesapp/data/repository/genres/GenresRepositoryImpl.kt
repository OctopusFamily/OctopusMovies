package com.octopus.moviesapp.data.repository.genres

import com.octopus.moviesapp.data.remote.pagingsource.GenreMoviesPagingSource
import com.octopus.moviesapp.data.remote.pagingsource.GenreTVShowsPagingSource
import com.octopus.moviesapp.data.remote.response.dto.GenreDTO
import com.octopus.moviesapp.data.remote.service.TMDBApiService
import com.octopus.moviesapp.domain.mapper.MoviesMapper
import com.octopus.moviesapp.domain.mapper.TVShowsMapper
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.domain.types.GenresType
import javax.inject.Inject

class GenresRepositoryImpl @Inject constructor(
    private val tmdbApiService: TMDBApiService,
    private val moviesMapper: MoviesMapper,
    private val tvShowsMapper: TVShowsMapper,
) : GenresRepository {
    override suspend fun getGenresByType(genresType: GenresType): List<GenreDTO> {
        return tmdbApiService.getGenresByType(genresType.pathName).items
    }

    override suspend fun getListOfMoviesByGenresId(genreId: Int,page: Int): List<Movie> {
        return moviesMapper.mapList(tmdbApiService.getMoviesByGenresId(genreId,page).items)
    }

    override suspend fun getListOfTVShowsByGenresId(genreId: Int, page: Int): List<TVShow> {
        return tvShowsMapper.mapList(tmdbApiService.getTVShowsByGenresId(genreId, page).items)
    }

    override  fun getGenreTVShowsPagingSource(genreId: Int): GenreTVShowsPagingSource {
        return GenreTVShowsPagingSource(tmdbApiService, genreId, tvShowsMapper)
    }

    override fun getGenreMoviesPagingSource(genreId: Int): GenreMoviesPagingSource {
        return GenreMoviesPagingSource(tmdbApiService, genreId, moviesMapper)
    }
}
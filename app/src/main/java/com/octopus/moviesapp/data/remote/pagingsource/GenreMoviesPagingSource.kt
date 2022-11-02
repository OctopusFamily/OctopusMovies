package com.octopus.moviesapp.data.remote.pagingsource

import com.octopus.moviesapp.data.remote.service.TMDBApiService
import com.octopus.moviesapp.domain.mapper.MoviesMapper
import com.octopus.moviesapp.domain.model.Movie

class GenreMoviesPagingSource(
    private val tmdbApiService: TMDBApiService,
    private val genreId: Int,
    private val moviesMapper: MoviesMapper
) : BasePagingSource<Movie>() {
    override suspend fun getData(page: Int): List<Movie> {
        return moviesMapper.mapList(tmdbApiService.getMoviesByGenresId(genreId, page).items)
    }
}

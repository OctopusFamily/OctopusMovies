package com.octopus.moviesapp.repositories.repository.genres

import com.octopus.moviesapp.android.response.dto.GenreDTO
import com.octopus.moviesapp.android.service.TMDBApiService

import com.octopus.moviesapp.android.local.types.GenresType
import com.octopus.moviesapp.android.response.dto.MovieDTO
import com.octopus.moviesapp.android.response.dto.TVShowDTO
import javax.inject.Inject

class GenresRepositoryImpl @Inject constructor(
    private val tmdbApiService: TMDBApiService,
) : GenresRepository {
    override suspend fun getGenresByType(genresType: GenresType): List<GenreDTO> {
        return tmdbApiService.getGenresByType(genresType.pathName).items
    }

    override suspend fun getListOfMoviesByGenresId(genreId: Int,page: Int): List<MovieDTO> {
        return tmdbApiService.getMoviesByGenresId(genreId,page).items
    }

    override suspend fun getListOfTVShowsByGenresId(genreId: Int, page: Int): List<TVShowDTO> {
        return tmdbApiService.getTVShowsByGenresId(genreId, page).items
    }

//    override  fun getGenreTVShowsPagingSource(genreId: Int): GenreTVShowsPagingSource {
//        return GenreTVShowsPagingSource(tmdbApiService, genreId, tvShowsMapper)
//    }
//
//    override fun getGenreMoviesPagingSource(genreId: Int): GenreMoviesPagingSource {
//        return GenreMoviesPagingSource(tmdbApiService, genreId, moviesMapper)
//    }
}
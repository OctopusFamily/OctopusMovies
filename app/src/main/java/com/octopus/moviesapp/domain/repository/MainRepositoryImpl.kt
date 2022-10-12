package com.octopus.moviesapp.domain.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.octopus.moviesapp.data.remote.request.ApiService
import com.octopus.moviesapp.domain.enums.GenresList
import com.octopus.moviesapp.domain.enums.MoviesCategory
import com.octopus.moviesapp.domain.enums.TVShowsCategory
import com.octopus.moviesapp.domain.mapper.GenresMapper
import com.octopus.moviesapp.domain.mapper.MoviesMapper
import com.octopus.moviesapp.domain.mapper.TVShowMapper
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.domain.pagerSourse.MoviesPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val moviesMapper: MoviesMapper,
    private val tvShowMapper: TVShowMapper,
    private val genresMapper: GenresMapper,
) : MainRepository {

    override suspend fun getMoviesByCategory(
        moviesCategory: MoviesCategory
    ): Flow<PagingData<Movie>> {
        Log.d("tests","GG")
        Log.d("tests",apiService.getMoviesByCategory("popular", page = 1).toString())
        return Pager(config = PagingConfig(pageSize = 20), pagingSourceFactory = {
            MoviesPagingSource(apiService, moviesMapper, moviesCategory.pathName)
        }).flow
    }

    override suspend fun getTVShowsByCategory(
        tvShowCategory: TVShowsCategory,
        page: Int
    ): List<TVShow> {
        return tvShowMapper.map(
            apiService.getTVShowsByCategory(tvShowCategory.pathName, 1).body()!!.items
        )
    }

    override suspend fun getGenresByList(
        genresByList: GenresList
    ): List<Genre> {
        return genresMapper.map(
            apiService.getGenresByList(genresByList.pathName).body()!!.itemsList, genresByList
        )
    }
}
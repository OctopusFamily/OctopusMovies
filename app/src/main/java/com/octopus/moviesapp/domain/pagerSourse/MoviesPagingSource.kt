package com.octopus.moviesapp.domain.pagerSourse

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.octopus.moviesapp.data.remote.request.ApiService
import com.octopus.moviesapp.domain.mapper.MoviesMapper
import com.octopus.moviesapp.domain.model.Movie

class MoviesPagingSource(
    private val apiService: ApiService,
    private val moviesMapper: MoviesMapper,
    private val moviesCategory: String,
) : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getMoviesByCategory(moviesCategory, page)
            return LoadResult.Page(
                data = moviesMapper.map(response.body()!!.items),
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (page == response.body()!!.totalPages) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
package com.octopus.moviesapp.domain.pagerSourse

import android.util.Log
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
        Log.d("tests","Inside Paging Source")
        return try {
            val page = params.key ?: 1
            val response = apiService.getMoviesByCategory(moviesCategory, page)
            Log.d("tests","Inside Paging source"+response.items.toString())
            return LoadResult.Page(
                data = moviesMapper.map(response.items),
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (page == response.totalPages) null else page.plus(1),
            )
        } catch (e: Exception) {
            Log.d("tests",e.message.toString())
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
package com.octopus.moviesapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.octopus.moviesapp.data.remote.response.BaseResponse

abstract class BasePagingSource<T : Any> : PagingSource<Int, T>() {

    abstract suspend fun getData(): BaseResponse<T>
    protected var queryPage = START_KEY

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(START_KEY) ?: anchorPage?.nextKey?.minus(START_KEY)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        return try {
            val response = getData()
            val currentPage = params.key ?: START_KEY
            queryPage = currentPage
            val itemsPerPage = response.items.size
            val itemsBefore = (currentPage * itemsPerPage) - itemsPerPage

            LoadResult.Page(
                data = response.items,
                prevKey = getPerKey(currentPage),
                nextKey = getNextKey(currentPage, itemsPerPage),
                itemsBefore = itemsBefore,
                itemsAfter = itemsPerPage,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private fun getNextKey(currentPage: Int, itemsPerPage: Int): Int? {
        return if (itemsPerPage == 0 || currentPage == TOTAL_PAGES) {
            null
        } else {
            currentPage.plus(START_KEY)
        }
    }

    private fun getPerKey(currentPage: Int): Int? {
        return if (currentPage == START_KEY) {
            null
        } else {
            currentPage.minus(START_KEY)
        }
    }

    companion object {
        private const val START_KEY = 1
        private const val TOTAL_PAGES = 500
    }

}
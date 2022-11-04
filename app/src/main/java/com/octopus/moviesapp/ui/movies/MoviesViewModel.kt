package com.octopus.moviesapp.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.use_case.GetMoviesPagingSourceUseCase
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.movies.uistate.MovieUiState
import com.octopus.moviesapp.ui.movies.uistate.MoviesMainUiState
import com.octopus.moviesapp.ui.search.ChipGroupClickListener
import com.octopus.moviesapp.util.Constants
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesPagingSourceUseCase: GetMoviesPagingSourceUseCase
) : BaseViewModel(), MoviesClicksListener, ChipGroupClickListener {

    private val _moviesMainUiState = MutableStateFlow(MoviesMainUiState())
    val moviesMainUiState: StateFlow<MoviesMainUiState> get() = _moviesMainUiState

    private val _navigateToMovieDetails = MutableLiveData<Event<Int>>()
    val navigateToMovieDetails: LiveData<Event<Int>> = _navigateToMovieDetails

    init {
        fetchMoviesByCategory()
    }

    private fun fetchMoviesByCategory() {
        val moviesUiStateFlow = Pager(PagingConfig(Constants.ITEMS_PER_PAGE, enablePlaceholders = true)) { getMoviesPagingSourceUseCase(moviesMainUiState.value.selectedChip.second) }.flow.cachedIn(viewModelScope).map { pagingData ->
            pagingData.map { movie -> movie.asMovieUiState() }
        }
        _moviesMainUiState.update { it.copy(moviesUiState = moviesUiStateFlow) }
    }

    override fun onMovieClick(movieId: Int) {
        _navigateToMovieDetails.postEvent(movieId)
    }

    override fun onChipSelected(selectedChipPosition: Int) {
//        _moviesMainUiState.update { it.copy(selectedChip = Pair(selectedChipPosition, getSelectedMoviesCategory(selectedChipPosition))) }
//        fetchMoviesByCategory()
    }

//    private fun getSelectedMoviesCategory(position: Int): MoviesCategory {
//        return when(position) {
//            0 -> MoviesCategory.POPULAR
//            1 -> MoviesCategory.NOW_PLAYING
//            2 -> MoviesCategory.UPCOMING
//            3 -> MoviesCategory.TOP_RATED
//            else -> MoviesCategory.POPULAR
//        }
//    }

    private fun Movie.asMovieUiState(): MovieUiState {
        return MovieUiState(
            id = id,
            title = title,
            released = releaseDate,
            rating = voteAverage,
            imageUrl = posterImageUrl,
        )
    }
}
package com.octopus.moviesapp.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.repository.MoviesRepository
import com.octopus.moviesapp.data.repository.TVShowsRepository
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.movies.MoviesClicksListener
import com.octopus.moviesapp.ui.tv_shows.TVShowsClicksListener
import com.octopus.moviesapp.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repositoryMovie: MoviesRepository,
    private val repositoryTVShow: TVShowsRepository
) : BaseViewModel(), MoviesClicksListener ,TVShowsClicksListener{

    private val _searchMovieState = MutableLiveData<UiState<List<Movie>>>(UiState.Loading)
    val searchMovieState: LiveData<UiState<List<Movie>>> get() = _searchMovieState

    private val _searchTVShowState = MutableLiveData<UiState<List<TVShow>>>(UiState.Loading)
    val searchTVShowState: LiveData<UiState<List<TVShow>>> get() = _searchTVShowState

    private val _isMovieChipChecked = MutableLiveData(true)
    val isMovieChipChecked: LiveData<Boolean> get() = _isMovieChipChecked



    val searchResult = MutableLiveData<String>("")


    fun getSearch() {
        when (isMovieChipChecked.value) {
            true -> {
                Log.e("search", "search movie")
                getSearchTVShow()
            }
            else -> {
                Log.e("search", "search tv show")
                getSearchMovie()
            }
        }
    }

    fun getSearchMovie() {
        viewModelScope.launch {
            wrapResponse { repositoryMovie.getSearchMovie(searchResult.value.toString()) }.collectLatest {
                _searchMovieState.postValue(it)
                _isMovieChipChecked.postValue(true)

                Log.e("search", _searchMovieState.value.toString())
                Log.e("search", searchResult.value.toString())
            }
        }
    }

    fun getSearchTVShow() {
        viewModelScope.launch {
            wrapResponse { repositoryTVShow.getSearchTVShow(searchResult.value.toString()) }.collectLatest {
                _searchTVShowState.postValue(it)
                _isMovieChipChecked.postValue(false)

                Log.e("search", _searchMovieState.value.toString())
                Log.e("search", searchResult.value.toString())
            }
        }
    }


    override fun onMovieClick(movieId: Int) {

    }

    override fun onTVShowClick(tvShow: TVShow) {

    }


}
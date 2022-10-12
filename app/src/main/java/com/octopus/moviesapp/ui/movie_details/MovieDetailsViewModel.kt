package com.octopus.moviesapp.ui.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.octopus.moviesapp.data.repository.MainRepository
import com.octopus.moviesapp.domain.enums.GenresType
import com.octopus.moviesapp.domain.model.Cast
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.model.Trailer
import com.octopus.moviesapp.domain.sealed.UiState
import com.octopus.moviesapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: MainRepository,
) : BaseViewModel() {

    private val _movieTrailerState = MutableLiveData<UiState<List<Trailer>>>(UiState.Loading)
    val movieTrailerState: LiveData<UiState<List<Trailer>>> get() = _movieTrailerState

    private val _movieCastState = MutableLiveData<UiState<List<Cast>>>(UiState.Loading)
    val movieCastState: LiveData<UiState<List<Cast>>> get() = _movieCastState

    private fun getMovieTrailerState(movieId: Int) {
        viewModelScope.launch {
            wrapResponse { repository.getMovieTrailersById(movieId) }.collectLatest {
                _movieTrailerState.postValue(it)
            }
        }
    }

    private fun getMovieCastState(movieId: Int) {
        viewModelScope.launch {
            wrapResponse { repository.getMovieCastById(movieId) }.collectLatest {
                _movieCastState.postValue(it)
            }
        }
    }

    private val _movieId = MutableLiveData<Int>()
    val movieId: LiveData<Int> = _movieId

    fun getMovieId(id: Int){
        _movieId.postValue(id)
    }
}
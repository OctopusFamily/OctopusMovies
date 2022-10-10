package com.octopus.moviesapp.ui.genres

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.repository.MainRepository
import com.octopus.moviesapp.domain.sealed.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class GenresViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _genreListState = MutableLiveData<UiState<List<Genre>>>(UiState.Loading)
    val genreListState: LiveData<UiState<List<Genre>>> get() = _genreListState

}
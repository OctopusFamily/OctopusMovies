package com.octopus.moviesapp.ui.genres

import com.octopus.moviesapp.domain.enums.GenresList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.repository.MainRepository
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.domain.sealed.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    private val repository: MainRepository
) : BaseViewModel(), GenresClicksListener {

    override fun onGenreClick(genreId: Int, genreType: GenresList) {

    }

    private val _genreListState = MutableLiveData<UiState<List<Genre>>>(UiState.Loading)
    val genreListState: LiveData<UiState<List<Genre>>> get() = _genreListState

}
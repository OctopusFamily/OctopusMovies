package com.octopus.moviesapp.ui.genres

import com.octopus.moviesapp.domain.enums.GenresList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.repository.MainRepository
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.domain.sealed.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    private val repository: MainRepository
) : BaseViewModel(), GenresClicksListener {



    private val _genreListState = MutableLiveData<UiState<List<Genre>>>(UiState.Loading)
    val genreListState: LiveData<UiState<List<Genre>>> get() = _genreListState

    private var currentGenresList = GenresList.TV


    init {
        getGenresByList(currentGenresList)
    }

    override fun onGenreClick(genreId: Int, genreType: GenresList) {}

    private fun getGenresByList(currentGenresList: GenresList) {
        viewModelScope.launch {
            wrapResponse { repository.getGenresByList(currentGenresList) }.collectLatest {
                _genreListState.postValue(it)
            }
        }
    }


    fun onTapClicked(genresList: GenresList) {
        if (genresList != currentGenresList) {
            getGenresByList(genresList)
            currentGenresList = genresList
        }
    }


}
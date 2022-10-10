package com.octopus.moviesapp.ui.genres

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.domain.enums.GenresList
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.repository.MainRepository
import com.octopus.moviesapp.domain.sealed.UiState
import com.octopus.moviesapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    private val repository: MainRepository
) : BaseViewModel(), GenresClicksListener {

    private val _genresListState = MutableLiveData<UiState<List<Genre>>>(UiState.Loading)
    val genresListState: LiveData<UiState<List<Genre>>> get() = _genresListState

    override fun onGenreClick(genreId: Int, genreType: GenresList) {

    }

    private fun getGenresByList(genresByList: GenresList) {
        viewModelScope.launch {
            wrapResponse { repository.getGenresByList(genresByList) }
                .collectLatest {
                    _genresListState.postValue(it)
                }
        }
    }
}
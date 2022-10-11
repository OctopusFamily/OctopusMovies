package com.octopus.moviesapp.ui.genres

import com.octopus.moviesapp.domain.enums.GenresType
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.data.repository.MainRepository
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

    private val _genresListState = MutableLiveData<UiState<List<Genre>>>(UiState.Loading)
    val genresListState: LiveData<UiState<List<Genre>>> get() = _genresListState

    private var currentGenresType = GenresType.TV

    init {
        getGenresByList(currentGenresType)
    }

    override fun onGenreClick(genreId: Int, genreType: GenresType) {}

    fun onTapSelected(genresType: GenresType) {
        getGenresByList(genresType)
        currentGenresType = genresType
    }

    fun tryLoadGenresAgain() {
        getGenresByList(currentGenresType)
    }

    private fun getGenresByList(currentGenresType: GenresType) {
        viewModelScope.launch {
            wrapResponse { repository.getGenresByType(currentGenresType) }.collectLatest {
                _genresListState.postValue(it)
            }
        }
    }
}
package com.octopus.moviesapp.ui.genres

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.domain.types.GenresType
import com.octopus.moviesapp.domain.use_case.genres.GetGenresByTypeUseCase
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.genres.mapper.GenresUiStateMapper
import com.octopus.moviesapp.ui.genres.uistate.GenresMainUiState
import com.octopus.moviesapp.ui.genres.uistate.GenresUiState
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    private val getGenresByTypeUseCase: GetGenresByTypeUseCase,
    private val genresUiStateMapper: GenresUiStateMapper
) : BaseViewModel(), GenresClicksListener {

    private val _genresListState = MutableStateFlow(GenresMainUiState())
    val genresListState: StateFlow<GenresMainUiState> get() = _genresListState

    private val _navigateToGenreMovie = MutableLiveData<Event<Int>>()
    val navigateToGenreMovie: LiveData<Event<Int>> get() = _navigateToGenreMovie

    private val _navigateToGenreTVShow = MutableLiveData<Event<Int>>()
    val navigateToGenreTVShow: LiveData<Event<Int>> get() = _navigateToGenreTVShow

    private var currentGenresType = GenresType.MOVIE

    init {
        currentGenresType
        getGenresByType(currentGenresType)
    }

    private fun getGenresByType(currentGenresType: GenresType) {
        viewModelScope.launch {
            try {
                val genres = getGenresByTypeUseCase(currentGenresType)
                val genresUiState = genresUiStateMapper.map(Pair(genres, currentGenresType))
                onSuccess(genresUiState)
            } catch (e: Exception) {
                onError()
            }
        }
    }

    private fun onSuccess(genresUiState: List<GenresUiState>) {
        _genresListState.update {
            it.copy(
                isLoading = false,
                isSuccess = true,
                genres = genresUiState
            )
        }
    }

    private fun onError() {
        _genresListState.update {
            it.copy(
                isLoading = false,
                isSuccess = false,
                isError = true
            )
        }
    }

    override fun onGenreClick(genre: GenresUiState) {
        when (genre.type.pathName) {
            "movie" -> _navigateToGenreMovie.postEvent(genre.id)
            "tv" -> _navigateToGenreTVShow.postEvent(genre.id)
        }
    }

    fun onTapSelected(genresType: GenresType) {
        currentGenresType = genresType
        getGenresByType(genresType)
    }

    fun tryLoadGenresAgain() {
        getGenresByType(currentGenresType)
    }
}
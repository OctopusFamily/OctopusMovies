package com.octopus.moviesapp.ui.genres

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.material.tabs.TabLayout
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
) : BaseViewModel(), GenresClicksListener, TabLayout.OnTabSelectedListener {

    private val _genresState = MutableStateFlow(GenresMainUiState())
    val genresState: StateFlow<GenresMainUiState> get() = _genresState

    private val _navigateToGenreMovie = MutableLiveData<Event<Pair<Int, String>>>()
    val navigateToGenreMovie: LiveData<Event<Pair<Int, String>>> get() = _navigateToGenreMovie

    private val _navigateToGenreTVShow = MutableLiveData<Event<Pair<Int, String>>>()
    val navigateToGenreTVShow: LiveData<Event<Pair<Int, String>>> get() = _navigateToGenreTVShow

    init {
        getGenresByType(genresState.value.selectedTab.second)
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
        _genresState.update {
            it.copy(
                isLoading = false,
                isSuccess = true,
                genres = genresUiState
            )
        }
    }

    private fun onError() {
        _genresState.update {
            it.copy(
                isLoading = false,
                isSuccess = false,
                isError = true
            )
        }
    }

    fun tryLoadGenresAgain() {
        getGenresByType(genresState.value.selectedTab.second)
    }

    override fun onGenreClick(genre: GenresUiState) {
        when (genre.type) {
            GenresType.MOVIE -> _navigateToGenreMovie.postEvent(Pair(genre.id, genre.name))
            GenresType.TV -> _navigateToGenreTVShow.postEvent(Pair(genre.id, genre.name))
        }
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        tab?.position?.let { position ->
            when (position) {
                0 -> {
                    _genresState.update { it.copy(selectedTab = Pair(position, GenresType.MOVIE)) }
                    getGenresByType(_genresState.value.selectedTab.second)
                }
                1 -> {
                    _genresState.update { it.copy(selectedTab = Pair(position, GenresType.TV)) }
                    getGenresByType(_genresState.value.selectedTab.second)
                }
            }
        }

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {}

    override fun onTabReselected(tab: TabLayout.Tab?) {}
}
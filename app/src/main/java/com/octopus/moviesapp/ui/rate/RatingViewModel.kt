package com.octopus.moviesapp.ui.rate

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.local.datastore.DataStorePref
import com.octopus.moviesapp.data.repository.movies.MoviesRepository
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.util.Constants
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RatingViewModel @Inject constructor(
    private val movieRepository: MoviesRepository,
    private val dataStorePref: DataStorePref,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val args = RatingMovieDialogArgs.fromSavedStateHandle(savedStateHandle)
    private var movieId = args.movieId

    var rate = MutableLiveData<Float>()

    private var sessionId = ""

    private val _closeClicked = MutableLiveData<Event<Boolean>>()
    val closeClicked: LiveData<Event<Boolean>> = _closeClicked

    private val _changeRating = MutableLiveData<Event<Boolean>>()
    val changeRating: LiveData<Event<Boolean>> get() = _changeRating

    private val _ratedDone = MutableLiveData<Event<Boolean>>()
    val ratedDone : LiveData<Event<Boolean>> get() =  _ratedDone

    fun onCloseClick() {
        _closeClicked.postEvent(true)
    }

    fun onRatingChange() {
        _changeRating.postEvent(true)
    }

    fun onRatingClick(rate: Float) {
        viewModelScope.launch {
            dataStorePref.readString(Constants.SESSION_ID_KEY).collect {
                it?.let {
                    Log.i("RATING_INFO", "sessionId = $it , movieId = $movieId")
                    sessionId = it

                    wrapResponse {
                        movieRepository.ratingMovie(movieId, sessionId, rate)
                    }.collect {
                        Log.i(
                            "RATING",
                            "collect ------ state is ${it.javaClass.simpleName} and data is ${it.toData()?.success}"
                        )
                        if (it is UiState.Success ){
                            _ratedDone.postEvent(true)
                        }
                    }
                }
            }
        }

    }

}

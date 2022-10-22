package com.octopus.moviesapp.ui.movie_details.dialogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.octopus.moviesapp.data.repository.movies.MoviesRepository
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.util.ConnectionTracker
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RateViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val connectionTracker: ConnectionTracker,
) : BaseViewModel() {
    private val _cancelClicked = MutableLiveData<Event<Boolean>>()
    val cancelClicked: LiveData<Event<Boolean>> get() = _cancelClicked

    val rating = MutableLiveData(3.0)

    fun onCancelClick() {
        _cancelClicked.postEvent(true)
    }


    fun onRateClick() {

    }

    fun onRateChange(rating: Double) {
        this.rating.postValue(rating)
    }
}
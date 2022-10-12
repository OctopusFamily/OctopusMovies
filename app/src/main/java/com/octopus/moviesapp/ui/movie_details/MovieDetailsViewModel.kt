package com.octopus.moviesapp.ui.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.octopus.moviesapp.data.repository.MainRepository
import com.octopus.moviesapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: MainRepository,
) : BaseViewModel() {

    private val _movieId = MutableLiveData<Int>()
    val movieId: LiveData<Int> = _movieId

    fun getMovieId(id: Int){
        _movieId.postValue(id)
    }
}
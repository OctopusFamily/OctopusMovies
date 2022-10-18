package com.octopus.moviesapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.local.database.entity.MovieEntity
import com.octopus.moviesapp.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.insertMovie(MovieEntity(0,"hello world","I'm testing"))
            repository.getAllMovies().collect{
                Log.i("TESTING",it.toString())
            }
        }
    }
}
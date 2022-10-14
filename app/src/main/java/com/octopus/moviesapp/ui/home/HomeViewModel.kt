package com.octopus.moviesapp.ui.home

import androidx.lifecycle.ViewModel
import com.octopus.moviesapp.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {
}
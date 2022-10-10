package com.octopus.moviesapp.ui.genres

import com.octopus.moviesapp.domain.enums.GenresList
import com.octopus.moviesapp.domain.repository.MainRepository
import com.octopus.moviesapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    private val repository: MainRepository
) : BaseViewModel(), GenresClicksListener {

    override fun onGenreClick(genreId: Int, genreType: GenresList) {

    }
}
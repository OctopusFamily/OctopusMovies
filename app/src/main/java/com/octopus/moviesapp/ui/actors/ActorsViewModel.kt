package com.octopus.moviesapp.ui.actors

import com.octopus.moviesapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActorsViewModel @Inject constructor(
) : BaseViewModel(), ActorsClicksListener {
    override fun onActorClick(personId: Int) {
        TODO("Not yet implemented")
    }


}
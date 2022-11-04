package com.octopus.moviesapp.android.viewmodels.nested

import com.octopus.moviesapp.android.viewmodels.base.BaseInteractionListener

interface NestedCastListener : BaseInteractionListener {
    fun onCastClick(castId: Int)
}
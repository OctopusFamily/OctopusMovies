package com.octopus.moviesapp.android.viewmodels.nested

import com.octopus.moviesapp.android.viewmodels.base.BaseInteractionListener

interface NestedImageTVShowListener : BaseInteractionListener {
    fun onImageTvShowClick(tvShowId: Int)
}
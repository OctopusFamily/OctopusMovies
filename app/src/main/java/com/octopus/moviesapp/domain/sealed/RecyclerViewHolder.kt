package com.octopus.moviesapp.domain.sealed

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.octopus.moviesapp.databinding.LayoutNestedCastBinding
import com.octopus.moviesapp.databinding.LayoutNestedInfoBinding
import com.octopus.moviesapp.domain.model.Cast
import com.octopus.moviesapp.domain.model.MovieDetails
import com.octopus.moviesapp.ui.nested.NestedCastAdapter
import com.octopus.moviesapp.ui.nested.NestedCastListener
import com.octopus.moviesapp.ui.nested.NestedGenresAdapter
import com.octopus.moviesapp.ui.nested.NestedGenresListener

sealed class RecyclerViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    class MovieDetailsInfoViewHolder(
        val binding: LayoutNestedInfoBinding
    ) : RecyclerViewHolder(binding) {
        fun bind(movieDetails: MovieDetails, genresListener: NestedGenresListener) {
            binding.run {
                taglineTextView.text = movieDetails.tagline
                overviewTextView.text = movieDetails.overview
                genresRecyclerView.adapter = NestedGenresAdapter(movieDetails.genres, genresListener)
            }
        }
    }

    class MovieDetailsCastViewHolder(
        val binding: LayoutNestedCastBinding
    ) : RecyclerViewHolder(binding) {
        fun bind(castList: List<Cast>, castListener: NestedCastListener) {
            binding.castRecyclerView.adapter = NestedCastAdapter(castList, castListener)
        }
    }
}

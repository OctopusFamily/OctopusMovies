package com.octopus.moviesapp.util

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.models.SlideModel
import com.octopus.moviesapp.databinding.LayoutNestedCastBinding
import com.octopus.moviesapp.databinding.LayoutNestedImageMovieBinding
import com.octopus.moviesapp.databinding.LayoutNestedImageSliderBinding
import com.octopus.moviesapp.databinding.LayoutNestedInfoBinding
import com.octopus.moviesapp.databinding.LayoutNestedSeasonsBinding
import com.octopus.moviesapp.domain.model.Cast
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.MovieDetails
import com.octopus.moviesapp.domain.model.Season
import com.octopus.moviesapp.domain.model.TVShowDetails
import com.octopus.moviesapp.ui.nested.NestedCastAdapter
import com.octopus.moviesapp.ui.nested.NestedCastListener
import com.octopus.moviesapp.ui.nested.NestedGenresAdapter
import com.octopus.moviesapp.ui.nested.NestedGenresListener
import com.octopus.moviesapp.ui.nested.NestedImageMovieAdapter
import com.octopus.moviesapp.ui.nested.NestedImageMovieListener
import com.octopus.moviesapp.ui.nested.NestedSeasonsAdapter
import com.octopus.moviesapp.ui.nested.NestedSeasonsListener

sealed class RecyclerViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    class MovieInfoViewHolder(
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

    class CastViewHolder(
        val binding: LayoutNestedCastBinding
    ) : RecyclerViewHolder(binding) {
        fun bind(castList: List<Cast>, castListener: NestedCastListener) {
            binding.castRecyclerView.adapter = NestedCastAdapter(castList, castListener)
        }
    }

    class TVShowInfoViewHolder(
        val binding: LayoutNestedInfoBinding
    ) : RecyclerViewHolder(binding) {
        fun bind(tvShowDetails: TVShowDetails, genresListener: NestedGenresListener) {
            binding.run {
                taglineTextView.text = tvShowDetails.tagline
                overviewTextView.text = tvShowDetails.overview
                genresRecyclerView.adapter =
                    NestedGenresAdapter(tvShowDetails.genres, genresListener)
            }
        }
    }

    class SeasonsViewHolder(
        val binding: LayoutNestedSeasonsBinding
    ) : RecyclerViewHolder(binding) {
        fun bind(seasonsList: List<Season>, seasonsListener: NestedSeasonsListener) {
            binding.seasonsRecyclerView.adapter =
                NestedSeasonsAdapter(seasonsList, seasonsListener)
        }
    }

    class ImageSliderViewHolder(
        val binding: LayoutNestedImageSliderBinding
    ) : RecyclerViewHolder(binding) {
        fun bind(title: String, imagesUrls: List<String>) {
            val imagesList = imagesUrls.map { SlideModel(it) }
            binding.imageSlider.setImageList(imagesList)
            binding.title.text = title
        }
    }

    class MovieImageViewHolder(
        val binding: LayoutNestedImageMovieBinding
    ) : RecyclerViewHolder(binding) {
        fun bind(movies: List<Movie>, listener: NestedImageMovieListener) {
            binding.run {
                movieImageRecyclerView.adapter = NestedImageMovieAdapter(movies, listener)
            }
        }
    }
}


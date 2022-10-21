package com.octopus.moviesapp.util

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.octopus.moviesapp.databinding.LayoutNestedCastBinding
import com.octopus.moviesapp.databinding.LayoutNestedImageSliderBinding
import com.octopus.moviesapp.databinding.LayoutNestedInfoBinding
import com.octopus.moviesapp.databinding.LayoutNestedSeasonsBinding
import com.octopus.moviesapp.domain.model.*
import com.octopus.moviesapp.ui.nested.*

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
        fun bind(title: String, trendingList: List<Trending>) {
            val imagesList = trendingList.map { trending ->
                SlideModel(imageUrl = trending.imageUrl, scaleType = ScaleTypes.FIT)
            }
            binding.run {
                imageSlider.setImageList(imagesList)
                titleTextView.text = title
            }
        }
    }
}

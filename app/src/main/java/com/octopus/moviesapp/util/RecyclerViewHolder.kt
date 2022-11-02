package com.octopus.moviesapp.util

import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.*
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.domain.model.Trending
import com.octopus.moviesapp.ui.home.nested.NestedMoviesAdapter
import com.octopus.moviesapp.ui.home.nested.NestedTVShowsAdapter
import com.octopus.moviesapp.ui.movie_details.uistate.MovieDetailsUiState
import com.octopus.moviesapp.ui.home.uistate.TrendingUiState
import com.octopus.moviesapp.ui.movies.MoviesClicksListener
import com.octopus.moviesapp.ui.movies.uistate.MovieUiState
import com.octopus.moviesapp.ui.nested.*
import com.octopus.moviesapp.ui.person_details.uistate.PersonDetailsUiState
import com.octopus.moviesapp.ui.person_details.uistate.PersonMovieUiState
import com.octopus.moviesapp.ui.person_details.uistate.PersonTVShowUiState
import com.octopus.moviesapp.ui.tv_show_details.uistate.CastUiState
import com.octopus.moviesapp.ui.tv_show_details.uistate.DetailsUiState
import com.octopus.moviesapp.ui.tv_show_details.uistate.SeasonUiState
import com.octopus.moviesapp.ui.tv_shows.TVShowsClicksListener
import com.octopus.moviesapp.ui.tv_shows.uistate.TVShowUiState

sealed class RecyclerViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    class MovieInfoViewHolder(
        val binding: LayoutNestedInfoBinding
    ) : RecyclerViewHolder(binding) {
        fun bind(movieDetails: MovieDetailsUiState, genresListener: NestedGenresListener) {
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
        fun bind(castList: List<CastUiState>, castListener: NestedCastListener) {
            binding.castRecyclerView.adapter = NestedCastAdapter(castList, castListener)
        }
    }

    class TVShowInfoViewHolder(
        val binding: LayoutNestedInfoBinding
    ) : RecyclerViewHolder(binding) {
        fun bind(tvShowDetails: DetailsUiState, genresListener: NestedGenresListener) {
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
        fun bind(seasonsList: List<SeasonUiState>, seasonsListener: NestedSeasonsListener) {
            binding.seasonsRecyclerView.adapter =
                NestedSeasonsAdapter(seasonsList, seasonsListener)
        }
    }

    class ImageSliderViewHolder(
        val binding: LayoutNestedImageSliderBinding
    ) : RecyclerViewHolder(binding) {
        fun bind(title: String, trendingList: List<TrendingUiState>) {
            val imagesList = trendingList.map { trending ->
                SlideModel(imageUrl = trending.imageUrl, scaleType = ScaleTypes.FIT)
            }
            binding.run {
                imageSlider.setImageList(imagesList)
                titleTextView.text = title
            }
        }
    }

    class MovieImageViewHolder(
        val binding: LayoutNestedImageMovieBinding
    ) : RecyclerViewHolder(binding) {
        fun bind(movies: List<PersonMovieUiState>, listener: NestedImageMovieListener) {
            binding.run {
                movieImageRecyclerView.adapter = NestedImageMovieAdapter(movies, listener)
            }
        }
    }

    class TvShowImageViewHolder(
        val binding: LayoutNestedImageTvShowBinding
    ) : RecyclerViewHolder(binding) {
        fun bind(tvShows: List<PersonTVShowUiState>, listener: NestedImageTVShowListener) {
            binding.run {
                tvshowImageRecyclerView.adapter = NestedImageTVShowAdapter(tvShows, listener)
            }
        }
    }

    class PersonInfoDetailsViewHolder(
        val binding: LayoutNestedPersonDetailsInfoBinding
    ) : RecyclerViewHolder(binding) {
        fun bind(personDetails: PersonDetailsUiState) {
            binding.run {
                biography.text = personDetails.biography
                birthday.text = personDetails.birthday
                pubular.text = personDetails.popularity.toString()
                job.text = personDetails.knownForDepartment
                home.text = personDetails.placeOfBirth
            }
        }
    }

    class MoviesViewHolder(
        val binding: LayoutNestedItemsBinding
    ) : RecyclerViewHolder(binding) {
        fun bind(moviesList: List<MovieUiState>, listener: MoviesClicksListener) {
            binding.run {
                titleTextView.text = this.root.context.getString(R.string.recommended_movies)
                nestedRecyclerView.adapter = NestedMoviesAdapter(moviesList, listener)
            }
        }
    }

    class TVShowsViewHolder(
        val binding: LayoutNestedItemsBinding
    ) : RecyclerViewHolder(binding) {
        fun bind(tvShowsList: List<TVShowUiState>, listener: TVShowsClicksListener) {
            binding.run {
                titleTextView.text = this.root.context.getString(R.string.recommended_tv_shows)
                nestedRecyclerView.adapter = NestedTVShowsAdapter(tvShowsList, listener)
            }
        }
    }

    class TrendingPeopleViewHolder(
        val binding: LayoutNestedImagesGridBinding
    ) : RecyclerViewHolder(binding) {
        fun bind(trendingPeopleList: List<TrendingUiState>) {
            binding.run {
                titleTextView.text = this.root.context.getString(R.string.trending_people)
                loadImage(firstImageView, trendingPeopleList[FIRST_PERSON].imageUrl)
                loadImage(secondImageView, trendingPeopleList[SECOND_PERSON].imageUrl)
                loadImage(thirdImageView, trendingPeopleList[THIRD_PERSON].imageUrl)
            }
        }

        private fun loadImage(imageView: ImageView, imageUrl: String?) {
            imageUrl?.let { url ->
                Glide.with(imageView).load(url).placeholder(R.drawable.rotate).error(R.drawable.ic_octopus_movies_logo).into(imageView)
            }
        }

        companion object {
            private const val FIRST_PERSON = 0
            private const val SECOND_PERSON = 1
            private const val THIRD_PERSON = 2
        }
    }
}

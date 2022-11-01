package com.octopus.moviesapp.util

import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.LayoutNestedCastBinding
import com.octopus.moviesapp.databinding.LayoutNestedImageMovieBinding
import com.octopus.moviesapp.databinding.LayoutNestedImageSliderBinding
import com.octopus.moviesapp.databinding.LayoutNestedImageTvShowBinding
import com.octopus.moviesapp.databinding.LayoutNestedImagesGridBinding
import com.octopus.moviesapp.databinding.LayoutNestedInfoBinding
import com.octopus.moviesapp.databinding.LayoutNestedItemsBinding
import com.octopus.moviesapp.databinding.LayoutNestedPersonDetailsInfoBinding
import com.octopus.moviesapp.databinding.LayoutNestedSeasonsBinding
import com.octopus.moviesapp.domain.model.Cast
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.MovieDetails
import com.octopus.moviesapp.domain.model.Season
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.domain.model.TVShowDetails
import com.octopus.moviesapp.domain.model.Trending
import com.octopus.moviesapp.ui.home.nested.NestedMoviesAdapter
import com.octopus.moviesapp.ui.home.nested.NestedTVShowsAdapter
import com.octopus.moviesapp.ui.movies.MoviesClicksListener
import com.octopus.moviesapp.ui.nested.NestedCastAdapter
import com.octopus.moviesapp.ui.nested.NestedCastListener
import com.octopus.moviesapp.ui.nested.NestedGenresAdapter
import com.octopus.moviesapp.ui.nested.NestedGenresListener
import com.octopus.moviesapp.ui.nested.NestedImageMovieAdapter
import com.octopus.moviesapp.ui.nested.NestedImageMovieListener
import com.octopus.moviesapp.ui.nested.NestedImageTvShowAdapter
import com.octopus.moviesapp.ui.nested.NestedImageTvShowListener
import com.octopus.moviesapp.ui.nested.NestedSeasonsAdapter
import com.octopus.moviesapp.ui.nested.NestedSeasonsListener
import com.octopus.moviesapp.ui.person_details.uistate.PersonDetailsUiState
import com.octopus.moviesapp.ui.person_details.uistate.PersonMovieUiState
import com.octopus.moviesapp.ui.person_details.uistate.PersonTvShowUiState
import com.octopus.moviesapp.ui.tv_shows.TVShowsClicksListener

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
        fun bind(tvShows: List<PersonTvShowUiState>, listener: NestedImageTvShowListener) {
            binding.run {
                tvshowImageRecyclerView.adapter = NestedImageTvShowAdapter(tvShows, listener)
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
        fun bind(moviesList: List<Movie>, listener: MoviesClicksListener) {
            binding.run {
                titleTextView.text = this.root.context.getString(R.string.recommended_movies)
                nestedRecyclerView.adapter = NestedMoviesAdapter(moviesList, listener)
            }
        }
    }

    class TVShowsViewHolder(
        val binding: LayoutNestedItemsBinding
    ) : RecyclerViewHolder(binding) {
        fun bind(tvShowsList: List<TVShow>, listener: TVShowsClicksListener) {
            binding.run {
                titleTextView.text = this.root.context.getString(R.string.recommended_tv_shows)
                nestedRecyclerView.adapter = NestedTVShowsAdapter(tvShowsList, listener)
            }
        }
    }

    class TrendingPeopleViewHolder(
        val binding: LayoutNestedImagesGridBinding
    ) : RecyclerViewHolder(binding) {
        fun bind(trendingPeopleList: List<Trending>) {
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

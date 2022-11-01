package com.octopus.moviesapp.ui.person_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.types.RecyclerViewItemType
import com.octopus.moviesapp.ui.base.BaseDiffUtil
import com.octopus.moviesapp.ui.nested.NestedImageMovieListener
import com.octopus.moviesapp.ui.nested.NestedImageTVShowListener
import com.octopus.moviesapp.util.RecyclerViewHolder
import com.octopus.moviesapp.util.RecyclerViewItem

class PersonDetailsAdapter(
    private var itemsList: List<RecyclerViewItem>,
    private val nestedImageMovieListener: NestedImageMovieListener,
    private val nestedImageTvShowListener: NestedImageTVShowListener,
) : RecyclerView.Adapter<RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return when (viewType) {

            RecyclerViewItemType.PERSON_INFO_DETAILS.ordinal -> {
                RecyclerViewHolder.PersonInfoDetailsViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.layout_nested_person_details_info,
                        parent,
                        false
                    )
                )
            }

            RecyclerViewItemType.MOVIE_IMAGE.ordinal -> {
                RecyclerViewHolder.MovieImageViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.layout_nested_image_movie,
                        parent,
                        false
                    )
                )
            }

            RecyclerViewItemType.TV_SHOW_IMAGE.ordinal -> {
                RecyclerViewHolder.TvShowImageViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.layout_nested_image_tv_show,
                        parent,
                        false
                    )
                )
            }

            else -> throw IllegalArgumentException("UNKNOWN VIEW TYPE")
        }
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        when (holder) {
            is RecyclerViewHolder.PersonInfoDetailsViewHolder -> {
                val item = itemsList[position] as RecyclerViewItem.PersonInfoDetailsItem
                holder.bind(item.personDetails)
            }

            is RecyclerViewHolder.MovieImageViewHolder -> {
                val item = itemsList[position] as RecyclerViewItem.ImageMovieItem
                holder.bind(item.movie, nestedImageMovieListener)
            }

            is RecyclerViewHolder.TvShowImageViewHolder -> {
                val item = itemsList[position] as RecyclerViewItem.ImageTvShowItem
                holder.bind(item.tvShows, nestedImageTvShowListener)
            }

            else -> {}
        }
    }

    override fun getItemCount(): Int = itemsList.size

    override fun getItemViewType(position: Int): Int {
        return when (itemsList[position]) {
            is RecyclerViewItem.PersonInfoDetailsItem -> RecyclerViewItemType.PERSON_INFO_DETAILS.ordinal
            is RecyclerViewItem.ImageMovieItem -> RecyclerViewItemType.MOVIE_IMAGE.ordinal
            is RecyclerViewItem.ImageTvShowItem -> RecyclerViewItemType.TV_SHOW_IMAGE.ordinal
            else -> -1
        }
    }

    fun setItems(newList: List<RecyclerViewItem>) {
        val differCallback = BaseDiffUtil(
            oldList = itemsList,
            newList = newList,
            { oldItem, newItem -> oldItem == newItem },
            { oldItem, newItem -> oldItem == newItem },
        )
        val diffResult = DiffUtil.calculateDiff(differCallback)
        itemsList = newList
        diffResult.dispatchUpdatesTo(this)
    }
}
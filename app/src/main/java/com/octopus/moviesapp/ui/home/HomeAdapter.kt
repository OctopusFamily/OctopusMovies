package com.octopus.moviesapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.types.RecyclerViewItemType
import com.octopus.moviesapp.ui.base.BaseDiffUtil
import com.octopus.moviesapp.ui.movies.MoviesClicksListener
import com.octopus.moviesapp.ui.tv_shows.TVShowsClicksListener
import com.octopus.moviesapp.util.RecyclerViewHolder
import com.octopus.moviesapp.util.RecyclerViewItem

class HomeAdapter(
    private var itemsList: List<RecyclerViewItem>,
    private val moviesClicksListener: MoviesClicksListener,
    private val tvShowsClicksListener: TVShowsClicksListener,
) : RecyclerView.Adapter<RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return when(viewType) {
            RecyclerViewItemType.IMAGE_SLIDER_VIEW_TYPE.ordinal -> RecyclerViewHolder.ImageSliderViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_nested_image_slider, parent, false))
            RecyclerViewItemType.MOVIES_ITEM_VIEW_TYPE.ordinal -> RecyclerViewHolder.MoviesViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_nested_items, parent, false))
            RecyclerViewItemType.TV_SHOWS_ITEM_VIEW_TYPE.ordinal -> RecyclerViewHolder.TVShowsViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_nested_items, parent, false))
            RecyclerViewItemType.TRENDING_PEOPLE_ITEM_VIEW_HOLDER.ordinal -> RecyclerViewHolder.TrendingPeopleViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_nested_images_grid, parent, false))
            else -> throw IllegalArgumentException("UNKNOWN VIEW TYPE")
        }
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        when (holder) {
            is RecyclerViewHolder.ImageSliderViewHolder -> {
                val item = itemsList[position] as RecyclerViewItem.ImageSliderItem
                holder.bind(item.title, item.trendingList)
            }
            is RecyclerViewHolder.MoviesViewHolder -> {
                val item = itemsList[position] as RecyclerViewItem.MoviesItem
                holder.bind(item.moviesList, moviesClicksListener)
            }
            is RecyclerViewHolder.TVShowsViewHolder -> {
                val item = itemsList[position] as RecyclerViewItem.TVShowsItem
                holder.bind(item.tvShowsList, tvShowsClicksListener)
            }
            is RecyclerViewHolder.TrendingPeopleViewHolder -> {
                val item = itemsList[position] as RecyclerViewItem.TrendingPeopleItem
                holder.bind(item.trendingPeopleList)
            }
            else -> { }
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(itemsList[position]) {
            is RecyclerViewItem.ImageSliderItem -> RecyclerViewItemType.IMAGE_SLIDER_VIEW_TYPE.ordinal
            is RecyclerViewItem.MoviesItem -> RecyclerViewItemType.MOVIES_ITEM_VIEW_TYPE.ordinal
            is RecyclerViewItem.TVShowsItem -> RecyclerViewItemType.TV_SHOWS_ITEM_VIEW_TYPE.ordinal
            is RecyclerViewItem.TrendingPeopleItem -> RecyclerViewItemType.TRENDING_PEOPLE_ITEM_VIEW_HOLDER.ordinal
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
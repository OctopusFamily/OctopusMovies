package com.octopus.moviesapp.ui.movie_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.enums.RecyclerViewItemType
import com.octopus.moviesapp.domain.sealed.RecyclerViewHolder
import com.octopus.moviesapp.domain.sealed.RecyclerViewItem
import com.octopus.moviesapp.ui.nested.NestedCastListener
import com.octopus.moviesapp.ui.nested.NestedGenresListener

class MovieDetailsAdapter(
    private var itemsList: List<RecyclerViewItem>,
    private val nestedGenresListener: NestedGenresListener,
    private val nestedCastListener: NestedCastListener,
) : RecyclerView.Adapter<RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return when (viewType) {
            RecyclerViewItemType.MOVIE_DETAILS_INFO_VIEW_TYPE.ordinal -> {
                RecyclerViewHolder.MovieDetailsInfoViewHolder(DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context), R.layout.layout_nested_info, parent, false))
            }
            RecyclerViewItemType.MOVIE_DETAILS_CAST_VIEW_TYPE.ordinal -> {
                RecyclerViewHolder.MovieDetailsCastViewHolder(DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context), R.layout.layout_nested_cast, parent, false))
            }
            else -> throw IllegalArgumentException("UNKNOWN VIEW TYPE")
        }
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        when (holder) {
            is RecyclerViewHolder.MovieDetailsInfoViewHolder -> {
                val item = itemsList[position] as RecyclerViewItem.MovieDetailsInfoItem
                holder.bind(item.movieDetails, nestedGenresListener)
            }
            is RecyclerViewHolder.MovieDetailsCastViewHolder -> {
                val item = itemsList[position] as RecyclerViewItem.MovieDetailsCastItem
                holder.bind(item.castList, nestedCastListener)
            }
        }
    }

    override fun getItemCount(): Int = itemsList.size

    override fun getItemViewType(position: Int): Int {
        return when(itemsList[position]) {
            is RecyclerViewItem.MovieDetailsInfoItem -> RecyclerViewItemType.MOVIE_DETAILS_INFO_VIEW_TYPE.ordinal
            is RecyclerViewItem.MovieDetailsCastItem -> RecyclerViewItemType.MOVIE_DETAILS_CAST_VIEW_TYPE.ordinal
            else -> -1
        }
    }

    fun setItems(newList: List<RecyclerViewItem>) {
        itemsList = newList
        notifyDataSetChanged()
    }
}
package com.octopus.moviesapp.ui.movie_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.types.RecyclerViewItemType
import com.octopus.moviesapp.ui.nested.NestedCastListener
import com.octopus.moviesapp.ui.nested.NestedGenresListener
import com.octopus.moviesapp.util.RecyclerViewHolder
import com.octopus.moviesapp.util.RecyclerViewItem

class MovieDetailsAdapter(
    private var itemsList: List<RecyclerViewItem>,
    private val nestedGenresListener: NestedGenresListener,
    private val nestedCastListener: NestedCastListener,
) : RecyclerView.Adapter<RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return when (viewType) {
            RecyclerViewItemType.MOVIE_INFO_VIEW_TYPE.ordinal -> {
                RecyclerViewHolder.MovieInfoViewHolder(DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context), R.layout.layout_nested_info, parent, false))
            }
            RecyclerViewItemType.CAST_VIEW_TYPE.ordinal -> {
                RecyclerViewHolder.CastViewHolder(DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context), R.layout.layout_nested_cast, parent, false))
            }
            else -> throw IllegalArgumentException("UNKNOWN VIEW TYPE")
        }
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        when (holder) {
            is RecyclerViewHolder.MovieInfoViewHolder -> {
                val item = itemsList[position] as RecyclerViewItem.MovieInfoItem
                holder.bind(item.movieDetails, nestedGenresListener)
            }
            is RecyclerViewHolder.CastViewHolder -> {
                val item = itemsList[position] as RecyclerViewItem.CastItem
                holder.bind(item.castList, nestedCastListener)
            }
            else -> {}
        }
    }

    override fun getItemCount(): Int = itemsList.size

    override fun getItemViewType(position: Int): Int {
        return when(itemsList[position]) {
            is RecyclerViewItem.MovieInfoItem -> RecyclerViewItemType.MOVIE_INFO_VIEW_TYPE.ordinal
            is RecyclerViewItem.CastItem -> RecyclerViewItemType.CAST_VIEW_TYPE.ordinal
            else -> -1
        }
    }

    fun setItems(newList: List<RecyclerViewItem>) {
        itemsList = newList
        notifyDataSetChanged()
    }
}
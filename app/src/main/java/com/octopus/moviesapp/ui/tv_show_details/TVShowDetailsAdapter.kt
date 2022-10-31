package com.octopus.moviesapp.ui.tv_show_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.types.RecyclerViewItemType
import com.octopus.moviesapp.ui.base.BaseDiffUtil
import com.octopus.moviesapp.util.RecyclerViewHolder
import com.octopus.moviesapp.util.RecyclerViewItem
import com.octopus.moviesapp.ui.nested.NestedCastListener
import com.octopus.moviesapp.ui.nested.NestedGenresListener
import com.octopus.moviesapp.ui.nested.NestedSeasonsListener

class TVShowDetailsAdapter(
    private var itemsList: List<RecyclerViewItem>,
    private val nestedGenresListener: NestedGenresListener,
    private val nestedCastListener: NestedCastListener,
    private val nestedSeasonsListener: NestedSeasonsListener,
) : RecyclerView.Adapter<RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return when (viewType) {
            RecyclerViewItemType.TV_SHOW_INFO_VIEW_TYPE.ordinal -> {
                RecyclerViewHolder.TVShowInfoViewHolder(DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context), R.layout.layout_nested_info, parent, false))
            }
            RecyclerViewItemType.CAST_VIEW_TYPE.ordinal -> {
                RecyclerViewHolder.CastViewHolder(DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context), R.layout.layout_nested_cast, parent, false))
            }
            RecyclerViewItemType.SEASONS_VIEW_TYPE.ordinal -> {
                RecyclerViewHolder.SeasonsViewHolder(DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context), R.layout.layout_nested_seasons, parent, false))
            }
            else -> throw IllegalArgumentException("UNKNOWN VIEW TYPE")
        }
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        when (holder) {
            is RecyclerViewHolder.TVShowInfoViewHolder -> {
                val item = itemsList[position] as RecyclerViewItem.TVShowInfoItem
                holder.bind(item.tvShowDetails, nestedGenresListener)
            }
            is RecyclerViewHolder.CastViewHolder -> {
                val item = itemsList[position] as RecyclerViewItem.CastItem
                holder.bind(item.castList, nestedCastListener)
            }
            is RecyclerViewHolder.SeasonsViewHolder -> {
                val item = itemsList[position] as RecyclerViewItem.SeasonItem
                holder.bind(item.seasonsList, nestedSeasonsListener)
            }

            else -> {}
        }
    }

    override fun getItemCount(): Int = itemsList.size

    override fun getItemViewType(position: Int): Int {
        return when(itemsList[position]) {
            is RecyclerViewItem.TVShowInfoItem -> RecyclerViewItemType.TV_SHOW_INFO_VIEW_TYPE.ordinal
            is RecyclerViewItem.CastItem -> RecyclerViewItemType.CAST_VIEW_TYPE.ordinal
            is RecyclerViewItem.SeasonItem -> RecyclerViewItemType.SEASONS_VIEW_TYPE.ordinal
            else -> -1
        }
    }

    fun setItems(newList: List<RecyclerViewItem>) {
//        val differCallback = BaseDiffUtil(
//            oldList = itemsList,
//            newList = newList,
//            { oldItem, newItem -> oldItem == newItem },
//            { oldItem, newItem -> oldItem == newItem },
//        )
//        val diffResult = DiffUtil.calculateDiff(differCallback)
        itemsList = newList
//        diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }
}
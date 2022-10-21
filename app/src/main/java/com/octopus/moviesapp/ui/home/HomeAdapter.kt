package com.octopus.moviesapp.ui.home

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

class HomeAdapter(
    private var itemsList: List<RecyclerViewItem>,
    private val homeClicksListener: HomeClicksListener,
) : RecyclerView.Adapter<RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return when(viewType) {
            RecyclerViewItemType.IMAGE_SLIDER_VIEW_TYPE.ordinal -> RecyclerViewHolder.ImageSliderViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_nested_image_slider, parent, false))
            else -> throw IllegalArgumentException("UNKNOWN VIEW TYPE")
        }
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        when (holder) {
            is RecyclerViewHolder.ImageSliderViewHolder -> {
                val item = itemsList[position] as RecyclerViewItem.ImageSliderItem
                holder.bind(item.title, item.trendingList)
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
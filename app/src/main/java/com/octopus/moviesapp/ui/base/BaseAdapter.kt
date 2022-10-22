package com.octopus.moviesapp.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.octopus.moviesapp.BR

abstract class BaseAdapter<T>(
    var itemsList: List<T>,
    private val listener: BaseInteractionListener,
) : RecyclerView.Adapter<BaseAdapter.ItemViewHolder>() {
    @LayoutRes
    abstract fun layoutId(): Int

    class ItemViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = itemsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutId(),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.run {
            setVariable(BR.item, itemsList[position])
            setVariable(BR.listener, listener)
        }
    }

    protected open fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    abstract fun areContentsTheSame(oldItem: T, newItem: T): Boolean

    fun setItems(newList : List<T>) {
        val differCallback = BaseDiffUtil(
            oldList = itemsList,
            newList = newList,
            { oldItem, newItem -> areItemsTheSame(oldItem, newItem) },
            { oldItem, newItem -> areContentsTheSame(oldItem, newItem) },
        )
        val diffResult = DiffUtil.calculateDiff(differCallback)
        itemsList = newList
        diffResult.dispatchUpdatesTo(this)
    }
}
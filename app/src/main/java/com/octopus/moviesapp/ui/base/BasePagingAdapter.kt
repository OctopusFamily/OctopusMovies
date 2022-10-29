package com.octopus.moviesapp.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView
import com.octopus.moviesapp.BR

abstract class BasePagingAdapter<T: Any>(
    private val listener: BaseInteractionListener,
    differCallback: ItemCallback<T>,
) : PagingDataAdapter<T, BasePagingAdapter.PagingViewHolder>(differCallback) {
    class PagingViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    @LayoutRes
    abstract fun layoutId(): Int

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        holder.binding.run {
            setVariable(BR.item, getItem(position))
            setVariable(BR.listener, listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        return PagingViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId(), parent,false))
    }
}
package com.octopus.moviesapp.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.DifferCallback
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.octopus.moviesapp.BR
import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.ui.movies.MoviesClicksListener

abstract class BasePagingAdapter<T: Any>(
    private val listener: BaseInteractionListener,
    differCallback: DiffUtil.ItemCallback<T>,
) : PagingDataAdapter<T, BasePagingAdapter.PagingItemViewHolder>(differCallback) {
    class PagingItemViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    @LayoutRes
    abstract fun layoutId(): Int

    override fun onBindViewHolder(holder: PagingItemViewHolder, position: Int) {
        holder.binding.run {
            setVariable(BR.item, getItem(position))
            setVariable(BR.listener, listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingItemViewHolder {
        return PagingItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId(), parent, false))
    }
}
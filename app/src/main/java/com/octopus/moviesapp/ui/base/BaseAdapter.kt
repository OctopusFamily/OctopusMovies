package com.octopus.moviesapp.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.octopus.moviesapp.BR

abstract class BaseAdapter<T>(
    var itemsList: List<T>,
    private val listener: BaseInteractionListener
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
}
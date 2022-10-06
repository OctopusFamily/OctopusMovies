package com.octopus.moviesapp.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.octopus.moviesapp.BR

abstract class BaseAdapter<T>(var itemsList: List<T>) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {
    @LayoutRes
    abstract fun layoutId(): Int

    class BaseViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = itemsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId(), parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.binding.run {
            setVariable(BR.item, itemsList[position])
        }
    }
}
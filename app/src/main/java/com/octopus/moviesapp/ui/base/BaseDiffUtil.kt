package com.octopus.moviesapp.ui.base

import androidx.recyclerview.widget.DiffUtil

class BaseDiffUtil<T>(
    private val oldList: List<T>,
    private val newList: List<T>,
    private val areSameItems: (oldItem: T, newItem: T) -> Boolean,
    private val areSameContents: (oldItem: T, newItem: T) -> Boolean,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areSameItems(oldList[oldItemPosition], newList[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areSameContents(oldList[oldItemPosition], newList[newItemPosition])
    }
}
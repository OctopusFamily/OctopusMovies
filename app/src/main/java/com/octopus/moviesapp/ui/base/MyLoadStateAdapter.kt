package com.octopus.moviesapp.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.ItemLoadStateBinding

class MyLoadStateAdapter(
    private val retry: () -> Unit,
) : LoadStateAdapter<MyLoadStateAdapter.LoadStateViewHolder>() {

    class LoadStateViewHolder(val binding: ItemLoadStateBinding, retry: () -> Unit) : RecyclerView.ViewHolder(binding.root) {
        private val progressBar = binding.progressBar
        private val errorLayout = binding.errorLinearLayout
        private val retryImageView = binding.retryImageView.also { it.setOnClickListener { retry() } }

        fun bind(loadState: LoadState) {
            progressBar.isVisible = loadState is LoadState.Loading
            errorLayout.isVisible = loadState is LoadState.Error
            retryImageView.isVisible = loadState is LoadState.Error
        }
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_load_state, parent, false), retry = retry)
    }

}
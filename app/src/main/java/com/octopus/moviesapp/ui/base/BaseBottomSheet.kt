package com.octopus.moviesapp.ui.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.octopus.moviesapp.R

class BaseBottomSheet<VDB: ViewDataBinding>(context: Context, @LayoutRes layoutId: Int) : BottomSheetDialog(context) {
    val binding: VDB = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, null, false)
    init {
        setContentView(binding.root)
    }
}
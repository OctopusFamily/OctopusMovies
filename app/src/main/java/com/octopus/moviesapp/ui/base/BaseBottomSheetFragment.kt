package com.octopus.moviesapp.ui.base

import android.os.Bundle
import android.view.*
import androidx.databinding.*
import androidx.lifecycle.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.octopus.moviesapp.BR


abstract class BaseBottomSheetFragment <VDB : ViewDataBinding> : BottomSheetDialogFragment(){
    abstract val layoutIdFragment: Int
    abstract val viewModel: ViewModel

    private lateinit var _binding: VDB
    protected val binding: VDB
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutIdFragment, container, false)
        _binding.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewModel,viewModel)
            return root
        }
    }
}
package com.octopus.moviesapp.ui.lists

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentMyListsBinding
import com.octopus.moviesapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyListsFragment : BaseFragment<FragmentMyListsBinding>() {
    override val viewModel: MyListsViewModel by viewModels()
    override fun getLayoutId(): Int = R.layout.fragment_my_lists
    private lateinit var myListsAdapter: MyListsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        observeLiveData()
    }

    private fun observeEvents() {
        // Add events here!
    }

    private fun observeLiveData() {
        viewModel.myListsState.observe(viewLifecycleOwner) { uiState ->
            uiState.toData()?.let { myLists -> myListsAdapter.setItems(myLists) }
        }
    }

    private fun setAdapter() {
        myListsAdapter = MyListsAdapter(emptyList(), viewModel)
        binding.myLists.adapter = myListsAdapter
    }
}
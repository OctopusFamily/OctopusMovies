package com.octopus.moviesapp.ui.lists

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentAllListsBinding
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.util.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllListsFragment : BaseFragment<FragmentAllListsBinding>() {
    override val viewModel: AllListsViewModel by viewModels()
    override fun getLayoutId(): Int = R.layout.fragment_all_lists
    private lateinit var createdListAdapter: CreatedListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeEvents()
        setAdapter()
    }

    private fun observeEvents() {
        viewModel.createdList.observe(viewLifecycleOwner){ createdListsState ->
            createdListsState.toData()?.let { items -> createdListAdapter.setItems(items) }
        }
        viewModel.isNewListClicked.observeEvent(viewLifecycleOwner){
            if (it){
                navigateToCreateListDialog()
            }
        }
     }

    private fun navigateToCreateListDialog() {
        findNavController().navigate(AllListsFragmentDirections.actionAllListsFragmentToCreateListDialog())
     }

    private fun setAdapter(){
        createdListAdapter = CreatedListAdapter(emptyList(), viewModel)
        binding.myLists.adapter = createdListAdapter
    }
}
package com.octopus.moviesapp.ui.lists

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentMyListsBinding
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.util.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyListsFragment : BaseFragment<FragmentMyListsBinding>() {
    override val viewModel: MyListsViewModel by viewModels()
    override fun getLayoutId(): Int = R.layout.fragment_my_lists
    private lateinit var myListsAdapter: MyListsAdapter
    private val args: MyListsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        observeLiveData()
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.isNewListClicked.observeEvent(viewLifecycleOwner){
            if (it){
                showCreateListDialog()
            }
        }

        viewModel.myListsState.observe(viewLifecycleOwner) {
            if (it is UiState.Success)
            viewModel.checkIfEmptyList()
        }

       viewModel.item.observeEvent(viewLifecycleOwner){
           val action =
               MyListsFragmentDirections.actionMyListsFragmentToListDetailsFragment(it.id,it.name)
           findNavController().navigate(action)
       }
     }

    private fun showCreateListDialog() {
        findNavController().navigate(
            MyListsFragmentDirections.actionMyListsFragmentToCreateListDialog(args.sessionId))
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
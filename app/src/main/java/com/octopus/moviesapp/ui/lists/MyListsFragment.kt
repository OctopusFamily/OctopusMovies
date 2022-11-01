package com.octopus.moviesapp.ui.lists

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentMyListsBinding
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.util.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyListsFragment : BaseFragment<FragmentMyListsBinding>() {
    override val viewModel: MyListsViewModel by activityViewModels()
    override fun getLayoutId(): Int = R.layout.fragment_my_lists
    private lateinit var myListsAdapter: MyListsAdapter
    private val args: MyListsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        observeLiveData()
        observeEvents()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getData()
    }

    private fun observeEvents() {
        viewModel.isNewListClicked.observeEvent(viewLifecycleOwner){
            if (it){
                showCreateListDialog()
            }
        }

        viewModel.item.observeEvent(viewLifecycleOwner){
            val action =
                MyListsFragmentDirections.actionMyListsFragmentToListDetailsFragment(it.listID,it.listName)
            findNavController().navigate(action)
        }

        viewModel.toastErrorMessage.observeEvent(viewLifecycleOwner){
            if (it){
                Toast.makeText(requireContext(), viewModel.createdListsUIState.value.error, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showCreateListDialog() {
        findNavController().navigate(
            MyListsFragmentDirections.actionMyListsFragmentToCreateListDialog(args.sessionID))
    }

    private fun observeLiveData() {
        lifecycleScope.launch {
            viewModel.createdListsUIState.collect {
                it.createdLists.let { myLists -> myListsAdapter.setItems(myLists) }
            }
        }
    }

    private fun setAdapter() {
        myListsAdapter = MyListsAdapter(emptyList(), viewModel)
        binding.myLists.adapter = myListsAdapter
     }
}
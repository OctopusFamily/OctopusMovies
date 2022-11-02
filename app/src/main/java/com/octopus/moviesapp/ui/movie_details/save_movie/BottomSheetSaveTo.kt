package com.octopus.moviesapp.ui.movie_details.save_movie

import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.BottomSheetSaveToBinding
import com.octopus.moviesapp.ui.base.BaseBottomSheetFragment
import com.octopus.moviesapp.util.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BottomSheetSaveTo : BaseBottomSheetFragment<BottomSheetSaveToBinding>() {
    override val viewModel: BottomSheetSaveToViewModel by viewModels()
    override val layoutIdFragment: Int = R.layout.bottom_sheet_save_to
    private lateinit var createdListsAdapter : BottomSheetSaveToAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        observeUIState()
        observeLiveEvents()
    }

    private fun observeUIState() {
        lifecycleScope.launch {
            viewModel.createdListsUIState.collect{
                it.createdLists.let { myLists -> createdListsAdapter.setItems(myLists) }
            }
        }

    }

    private fun observeLiveEvents() {
        viewModel.toastMessage.observe(viewLifecycleOwner){
            Toast.makeText(context,viewModel.toastMessage.value.toString(),LENGTH_LONG).show()
            dismiss()
        }
        viewModel.isNewListClicked.observeEvent(viewLifecycleOwner){
            if (it){
                findNavController().navigate(
                    BottomSheetSaveToDirections.actionBottomSheetSaveToToMyListsFragment(viewModel.sessionID)
                )
            }
        }
    }

    private fun setAdapter() {
        createdListsAdapter = BottomSheetSaveToAdapter(emptyList(), viewModel)
        binding.lists.adapter = createdListsAdapter
    }
}
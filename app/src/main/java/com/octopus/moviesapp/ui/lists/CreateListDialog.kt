package com.octopus.moviesapp.ui.lists

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.DialogCreateListBinding
import com.octopus.moviesapp.ui.base.BaseDialogFragment
import com.octopus.moviesapp.util.extensions.observeEvent
import com.octopus.moviesapp.util.extensions.setWidthPercent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateListDialog : BaseDialogFragment<DialogCreateListBinding>() {
    override val viewModel: MyListsViewModel by activityViewModels()
    override val layoutIdFragment: Int = R.layout.dialog_create_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidthPercent(90)
        observeEvents()

    }

    private fun observeEvents() {
        viewModel.onClickAddEvent.observeEvent(viewLifecycleOwner){
            if (it){
                dismiss()
            }
        }
        viewModel.isCloseClicked.observeEvent(viewLifecycleOwner){
            if (it){
                dismiss()
            }
        }
    }
}
package com.octopus.moviesapp.ui.movie_details.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.DialogRateBinding
import com.octopus.moviesapp.ui.base.BaseDialogFragment
import com.octopus.moviesapp.ui.movie_details.MovieDetailsViewModel
import com.octopus.moviesapp.util.extensions.observeEvent
import com.octopus.moviesapp.util.extensions.setWidthPercent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RateDialog : BaseDialogFragment<DialogRateBinding>() {
    override val layoutIdFragment: Int = R.layout.dialog_rate
    override val viewModel: RateViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidthPercent(80)
    }

    override fun onStart() {
        super.onStart()
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.cancelClicked.observeEvent(viewLifecycleOwner) {
            if (it) {
                dismiss()
            }
        }
        viewModel.rating.observe(viewLifecycleOwner) {
            Toast.makeText(this.context,it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

}
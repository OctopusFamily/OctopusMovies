package com.octopus.moviesapp.ui.rate

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.DialogRateBinding
import com.octopus.moviesapp.ui.base.BaseDialogFragment
import com.octopus.moviesapp.util.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RatingMovieDialog: BaseDialogFragment<DialogRateBinding>() {
    override val layoutIdFragment: Int = R.layout.dialog_rate
    override val viewModel: RatingViewModel by viewModels()
    private var rate = 0.0f

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.closeClicked.observeEvent(this) {
            dismiss()
        }
        viewModel.changeRating.observeEvent(this) {
            updateMovieRating()
        }
        viewModel.ratedDone.observeEvent(this) {
            dismiss()
        }

        viewModel.rate.observe(this) {
            getRate(it)
            rate = it
        }
    }

    private fun updateMovieRating() {
        Toast.makeText(requireContext(),"Rated: ${rate}", Toast.LENGTH_SHORT).show()
        viewModel.onRatingClick(rate)
    }

    private fun getRate(rate: Float){
        binding.textRateNumber.text = rate.toString()
    }
}
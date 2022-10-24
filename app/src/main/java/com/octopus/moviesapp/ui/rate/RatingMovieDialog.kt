package com.octopus.moviesapp.ui.rate

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.DialogRateBinding
import com.octopus.moviesapp.ui.base.BaseDialogFragment
import com.octopus.moviesapp.util.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RatingMovieDialog: BaseDialogFragment<DialogRateBinding>() {
    override val layoutIdFragment: Int = R.layout.dialog_rate
    override val viewModel: RatingViewModel by viewModels()
    private var rate = 0.0f

    private val args: RatingMovieDialogArgs by navArgs()

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
        viewModel.rate.observe(this) {
            getRate(it)
            rate = it
        }

    }

    private fun updateMovieRating() {
        Toast.makeText(requireContext(),"Rate: ${rate}", Toast.LENGTH_SHORT).show()
        viewModel.getMovieId(args.movieId)
        viewModel.onRatingClick(rate)
    }

    private fun getRate(rate: Float){
        binding.textRateNumber.text = rate.toString()
    }
}
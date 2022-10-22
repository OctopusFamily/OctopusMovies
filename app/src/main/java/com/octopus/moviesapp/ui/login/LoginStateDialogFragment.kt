package com.octopus.moviesapp.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.LoginStateDialogBinding
import com.octopus.moviesapp.ui.base.BaseDialogFragment
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.util.extensions.setWidthPercent
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class LoginStateDialogFragment : BaseDialogFragment<LoginStateDialogBinding>() {
    override val layoutIdFragment: Int = R.layout.login_state_dialog
    override val viewModel: LoginViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidthPercent(60)
        viewModel.login()
        observeEvents()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (viewModel.loginRequestState.value is UiState.Success) {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
        }
    }

    private fun observeEvents() {
        viewModel.loginRequestState.observe(viewLifecycleOwner) {
            if (it is UiState.Success) {
                dismiss()
            } else if (it is UiState.Error) {
                val timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        dialog?.dismiss()
                        timer.cancel()
                    }
                }, 4000)
            }
        }
    }
}

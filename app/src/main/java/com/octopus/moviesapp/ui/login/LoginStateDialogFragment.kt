package com.octopus.moviesapp.ui.login

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.LoginStateDialogBinding
import com.octopus.moviesapp.ui.base.BaseDialogFragment
import com.octopus.moviesapp.util.extensions.observeEvent
import com.octopus.moviesapp.util.extensions.setWidthPercent
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class LoginStateDialogFragment : BaseDialogFragment<LoginStateDialogBinding>() {
    override val viewModel: LoginViewModel by viewModels()
    override val layoutIdFragment: Int = R.layout.login_state_dialog


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setWidthPercent(60)
        viewModel.login()
        observeEvents()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (viewModel.loginMainUiState.value.isSuccess) {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
        }
    }

    private fun observeEvents() {
        viewModel.loginEvent.observeEvent(viewLifecycleOwner) {
            if (it) {
                dismiss()
            } else if (it == false) {
                Log.d("errorDialog","true")
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

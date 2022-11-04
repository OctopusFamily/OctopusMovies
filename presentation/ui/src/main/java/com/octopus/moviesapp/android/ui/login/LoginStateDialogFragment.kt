package com.octopus.moviesapp.android.ui.login

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import com.octopus.moviesapp.android.viewmodels.login.LoginViewModel
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

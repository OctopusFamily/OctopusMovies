package com.octopus.moviesapp.ui.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentLoginBinding
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.util.Constants
import com.octopus.moviesapp.util.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val viewModel: LoginViewModel by viewModels()
    override fun getLayoutId(): Int = R.layout.fragment_login
    override var bottomNavigationViewVisibility = View.GONE

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.signUpEvent.observeEvent(viewLifecycleOwner) {
            if (it) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(Constants.TMDB_SIGNUP_URL))
                startActivity(browserIntent)
            }
        }

        viewModel.skipLoginClicked.observeEvent(viewLifecycleOwner) {
            if (it) {
                navigateToHomeFragment()
            }
        }

        viewModel.isDialogShown.observe(viewLifecycleOwner) {
            if (it) {
                navigateToLoadingDialog()
            }
        }

        viewModel.forgotPasswordClicked.observeEvent(viewLifecycleOwner){
            if (it){
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(Constants.TMDB_RESET_PASSWORD_URL))
                startActivity(browserIntent)
            }
        }
    }

    private fun navigateToLoadingDialog() {
        val username = viewModel.username.value.toString()
        val password = viewModel.password.value.toString()
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToLoadingDialog(username,password))
    }

    private fun navigateToHomeFragment() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
    }

}
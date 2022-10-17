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
import com.octopus.moviesapp.util.observeEvent
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val viewModel: LoginViewModel by viewModels()
    override fun getLayoutId(): Int = R.layout.fragment_login

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

        viewModel.loginEvent.observeEvent(viewLifecycleOwner) {
            if (it)
             findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
        }
    }


}
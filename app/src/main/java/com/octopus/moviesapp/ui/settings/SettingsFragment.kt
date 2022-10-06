package com.octopus.moviesapp.ui.settings

import androidx.fragment.app.viewModels
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentSettingsBinding
import com.octopus.moviesapp.ui.base.BaseFragment

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_settings
    override val viewModel: SettingsViewModel by viewModels()
}
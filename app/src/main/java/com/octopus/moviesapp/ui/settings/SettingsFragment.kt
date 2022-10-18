package com.octopus.moviesapp.ui.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentSettingsBinding
import com.octopus.moviesapp.databinding.LayoutLanguageSelectionBinding
import com.octopus.moviesapp.databinding.LayoutThemeSelectionBinding
import com.octopus.moviesapp.domain.types.Language
import com.octopus.moviesapp.domain.types.Theme
import com.octopus.moviesapp.ui.base.BaseBottomSheet
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.ui.movies.MoviesFragmentDirections
import com.octopus.moviesapp.util.SettingsService
import com.octopus.moviesapp.util.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_settings
    override val viewModel: SettingsViewModel by viewModels()

    private val settingsService = SettingsService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleEvents()
    }

    private fun handleEvents() {
        viewModel.run {
            languageChoiceClicked.observeEvent(viewLifecycleOwner) { clicked ->
                if (clicked) {
                    showLanguageSelectionBottomSheet()
                }
            }
            themeChoiceClicked.observeEvent(viewLifecycleOwner) { clicked ->
                if (clicked) {
                    showThemeSelectionBottomSheet()
                }
            }
            navigateToAbout.observeEvent(viewLifecycleOwner) { clicked ->
                if (clicked) {
                    navigateToAbout()
                }
            }
        }
    }

    private fun showLanguageSelectionBottomSheet() {
        val currentLanguage = settingsService.getCurrentLanguage()
        BaseBottomSheet<LayoutLanguageSelectionBinding>(
            requireContext(),
            R.layout.layout_language_selection
        ).run {
            binding.run {
                arabicLanguageRadioButton.run {
                    isChecked = currentLanguage == Language.ARABIC
                    setOnClickListener {
                        if (currentLanguage != Language.ARABIC) {
                            handleLanguageChange(Language.ARABIC)
                            dismiss()
                        }
                    }
                }
                englishLanguageRadioButton.run {
                    isChecked = currentLanguage == Language.ENGLISH
                    setOnClickListener {
                        if (currentLanguage != Language.ENGLISH) {
                            handleLanguageChange(Language.ENGLISH)
                            dismiss()
                        }
                    }
                }
            }
            show()
        }
    }

    private fun handleLanguageChange(newLanguage: Language) {
        viewModel.handleLanguageChange(newLanguage)
    }

    private fun navigateToAbout() {
        requireView()
            .findNavController()
            .navigate(SettingsFragmentDirections.actionSettingsFragmentToAboutFragment())

    }

    private fun showThemeSelectionBottomSheet() {
        val currentTheme = settingsService.getCurrentAppTheme(requireContext())
        BaseBottomSheet<LayoutThemeSelectionBinding>(
            requireContext(),
            R.layout.layout_theme_selection
        ).run {
            binding.run {
                lightThemeRadioButton.run {
                    isChecked = currentTheme == Theme.LIGHT
                    setOnClickListener {
                        if (currentTheme != Theme.LIGHT) {
                            handleThemeChange(Theme.LIGHT)
                            dismiss()
                        }
                    }
                }
                darkThemeRadioButton.run {
                    isChecked = currentTheme == Theme.DARK
                    setOnClickListener {
                        if (currentTheme != Theme.DARK) {
                            handleThemeChange(Theme.DARK)
                            dismiss()
                        }
                    }
                }
            }
            show()
        }
    }

    private fun handleThemeChange(newTheme: Theme) {
        viewModel.handleThemeChange(newTheme)
    }
}

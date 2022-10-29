package com.octopus.moviesapp.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.octopus.moviesapp.MyApplication
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentDashboardBinding
import com.octopus.moviesapp.databinding.LayoutLanguageSelectionBinding
import com.octopus.moviesapp.databinding.LayoutThemeSelectionBinding
import com.octopus.moviesapp.domain.types.Language
import com.octopus.moviesapp.domain.types.Theme
import com.octopus.moviesapp.ui.base.BaseBottomSheet
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.util.SettingsService
import com.octopus.moviesapp.util.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_dashboard
    override val viewModel: DashboardViewModel by viewModels()
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
            loginClicked.observeEvent(viewLifecycleOwner) {
                if (it) {
                    navigateToLogin()
                }
            }
            logOutClicked.observeEvent(viewLifecycleOwner) {
                if (it) {
                    restartApp()
                }
            }
            navigateToMyLists.observeEvent(viewLifecycleOwner) { clicked ->
                if (clicked) {
                    navigateToMyLists()
                }
            }
        }
    }

    private fun navigateToLogin() {
        findNavController().navigate(
            R.id.action_settingsFragment_to_loginFragment
        )
    }

    private fun restartApp() {
        val intent = requireActivity().intent
        requireActivity().finish()
        startActivity(intent)
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
            .navigate(DashboardFragmentDirections.actionSettingsFragmentToAboutFragment())

    }

    private fun navigateToMyLists() {
        findNavController().navigate(
            DashboardFragmentDirections.actionSettingsFragmentToMyListsFragment(MyApplication.sessionId)
        )
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

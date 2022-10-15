package com.octopus.moviesapp.ui.settings

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.FragmentSettingsBinding
import com.octopus.moviesapp.databinding.LayoutLanguageSelectionBinding
import com.octopus.moviesapp.databinding.LayoutThemeSelectionBinding
import com.octopus.moviesapp.domain.enums.Language
import com.octopus.moviesapp.domain.enums.Theme
import com.octopus.moviesapp.ui.base.BaseFragment
import com.octopus.moviesapp.util.SettingsService
import com.octopus.moviesapp.util.SettingsService.updateBaseContextLocale
import com.octopus.moviesapp.util.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_settings
    override val viewModel: SettingsViewModel by viewModels()
    var currentLanguage = SettingsService.currentLanguage
    var currentTheme = SettingsService.currentTheme
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleEvents()
    }

    private fun handleEvents() {
        viewModel.run {
            languageChoiceClicked.observeEvent(viewLifecycleOwner) { clicked ->
                if (clicked) {
                    showChangeLanguageBottomSheet()
                }
            }
            newLanguageChose.observeEvent(viewLifecycleOwner) { chose ->
                if (chose) {
                    requireActivity().recreate()
                }
            }
            themeChoiceClicked.observeEvent(viewLifecycleOwner) { clicked ->
                if (clicked) {
                    showChangeThemeBottomSheet()
                }
            }
            newThemeChose.observeEvent(viewLifecycleOwner) { chose ->
                if (chose) {
                    requireActivity().recreate()
                }
            }
        }
    }

    private fun showChangeLanguageBottomSheet() {
        currentLanguage = viewModel.currentLanguage.value!!
        BottomSheetDialog(requireContext()).run {
            val binding = DataBindingUtil.inflate<LayoutLanguageSelectionBinding>(layoutInflater,
                R.layout.layout_language_selection,
                null,
                false)
            setContentView(binding.root)
            if (currentLanguage == Language.ENGLISH) {
                binding.englishLanguageRadioButton.isChecked = true
            } else {
                binding.arabicLanguageRadioButton.isChecked = true
            }
            binding.englishLanguageRadioButton.setOnClickListener {
                if (currentLanguage != Language.ENGLISH) {

                    SettingsService.currentLanguage = Language.ENGLISH
                    updateBaseContextLocale(requireContext())
                    handleLanguageChange(Language.ENGLISH)
                    dismiss()
                }
            }

            binding.arabicLanguageRadioButton.setOnClickListener {
                if (currentLanguage != Language.ARABIC) {
                    SettingsService.currentLanguage = Language.ARABIC
                    updateBaseContextLocale(requireContext())
                    handleLanguageChange(Language.ARABIC)
                    dismiss()
                }
            }
            show()
        }
    }

    private fun handleLanguageChange(newLanguage: Language) {
        viewModel.handleLanguageChange(newLanguage)
    }

    private fun showChangeThemeBottomSheet() {
        currentTheme = viewModel.currentLTheme.value!!
        BottomSheetDialog(requireContext()).run {
            val binding = DataBindingUtil.inflate<LayoutThemeSelectionBinding>(layoutInflater,
                R.layout.layout_theme_selection,
                null,
                false)
            setContentView(binding.root)
            if (currentTheme == Theme.LIGHT) {
                binding.lightThemeRadioButton.isChecked = true
            } else {
                binding.darkThemeRadioButton.isChecked = true
            }

            binding.lightThemeRadioButton.setOnClickListener {
                if (currentTheme != Theme.LIGHT) {
                    SettingsService.currentTheme = Theme.LIGHT
                    SettingsService.updateAppTheme()
                    handleThemeChange(Theme.LIGHT)
                    dismiss()
                }
            }

            binding.darkThemeRadioButton.setOnClickListener {
                if (currentTheme != Theme.DARK) {
                    SettingsService.currentTheme = Theme.DARK
                    SettingsService.updateAppTheme()
                    handleThemeChange(Theme.DARK)
                    dismiss()
                }
            }
            show()
        }
    }

    private fun handleThemeChange(newTheme: Theme) {
        viewModel.handleThemeChange(newTheme)
    }
}

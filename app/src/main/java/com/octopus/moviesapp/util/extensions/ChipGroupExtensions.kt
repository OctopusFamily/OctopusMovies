package com.octopus.moviesapp.util.extensions

import androidx.core.view.children
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

fun ChipGroup.getSelectedChipIndex(): Int {
    val selectedChip = this.children.filter { (it as Chip).isChecked }.first()
    return this.children.indexOf(selectedChip)
}
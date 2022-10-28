package com.octopus.moviesapp.util.extensions

import android.view.LayoutInflater
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.ItemChipBinding

fun ChipGroup.getSelectedChipIndex(): Int {
    val selectedChip = this.children.filter { (it as Chip).isChecked }.first()
    return this.children.indexOf(selectedChip)
}

fun ChipGroup.createChip(chipTitle: String, isChipSelected: Boolean) {
    val chip = DataBindingUtil.inflate<ItemChipBinding>(LayoutInflater.from(this.context), R.layout.item_chip, this, false).root as Chip
    chip.run {
        text = chipTitle
        isChecked = isChipSelected
    }
    this.addView(chip)
}
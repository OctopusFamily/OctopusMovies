package com.octopus.moviesapp.util

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.view.children
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.octopus.moviesapp.ui.trailer.TrailerActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.convertToDate(): Date {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale("en"))
    return formatter.parse(this) ?: Date()
}

fun <T> MutableLiveData<Event<T>>.postEvent(content: T) {
    postValue(Event(content))
}

inline fun <T> LiveData<Event<T>>.observeEvent(
    owner: LifecycleOwner,
    crossinline onEventUnhandledContent: (T) -> Unit
) {
    observe(owner) { it?.getContentIfNotHandled()?.let(onEventUnhandledContent) }
}

fun String.buildImageUrl(): String {
    return Constants.IMAGE_BASE_URL + this
}

fun Context.showShortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.navigateToTrailerActivity(trailerKey: String) {
    Intent(this, TrailerActivity::class.java).run {
        putExtra(Constants.TRAILER_KEY, trailerKey)
        startActivity(this)
    }
}

fun String.isEnglishLettersOnly(): Boolean {
    return this.matches("^[a-zA-Z]*$".toRegex())
}

fun String.isEnglishLettersAndDigitsOnly(): Boolean {
    return this.matches("^[a-zA-Z0-9]*$".toRegex())
}

fun ChipGroup.getSelectedChipIndex() : Int {
    val selectedChip = this.children.filter { (it as Chip).isChecked }.first()
    return this.children.indexOf(selectedChip)
}
package com.octopus.moviesapp.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.core.view.children
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.text.SimpleDateFormat
import java.util.*

fun buildImageUrl(path: String?): String {
    return path?.let { Constants.IMAGE_BASE_URL + path } ?: ""
}

fun convertStringToDate(dateString: String?): Date {
    return if (dateString.isNullOrEmpty()) {
        Date()
    } else {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale("en"))
        formatter.parse(dateString) ?: Date()
    }
}

fun getTextOrPlaceholder(context: Context, text: String?, @StringRes placeHolder: Int): String {
    return if (text.isNullOrEmpty()) {
        context.getString(placeHolder)
    } else {
        text
    }
}


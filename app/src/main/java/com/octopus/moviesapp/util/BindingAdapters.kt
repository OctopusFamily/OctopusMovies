package com.octopus.moviesapp.util

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.enums.Language
import com.octopus.moviesapp.domain.enums.Theme
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter(value = ["app:imageUrl"])
fun loadImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let { url ->
        Glide.with(imageView).load(url).placeholder(R.drawable.rotate).error(R.drawable.ic_octopus_movies_logo).into(imageView)
    }
}

@BindingAdapter(value = ["app:showWhenStateIsLoading"])
fun <T> showWhenUiStateIsLoading(view: View, uiState: UiState<T>) {
    view.isVisible = uiState is UiState.Loading
}

@BindingAdapter(value = ["app:showWhenStateIsSuccess"])
fun <T> showWhenUiStateIsSuccess(view: View, uiState: UiState<T>) {
    view.isVisible = uiState is UiState.Success
}

@BindingAdapter(value = ["app:showWhenStateIsError"])
fun <T> showWhenUiStateIsError(view: View, uiState: UiState<T>) {
    view.isVisible = uiState is UiState.Error
}

@BindingAdapter(value = ["app:releaseDate"])
fun setReleaseDate(view: TextView, date: Date?) {
    val formatDate = SimpleDateFormat("yyyy", Locale("en"))
    date?.let {
        view.text = formatDate.format(it).toString()
    }
}

@BindingAdapter(value = ["app:voteAverage"])
fun setVoteAverage(view: TextView, rating: Float?) {
    rating?.let {
        view.text = String.format("%.1f", it).toDouble().toString()
    }
}

@BindingAdapter(value = ["app:setRuntime"])
fun setRuntime(view: TextView, duration: Int?) {
    duration?.let {
        view.text = view.context.getString(R.string.duration, it)
    }
}

@BindingAdapter(value = ["app:setSeasons"])
fun setSeasons(view: TextView, seasonsNumber: Int?) {
    seasonsNumber?.let {
        when (it) {
            1 -> view.text = view.context.getString(R.string.season, it)
            else -> view.text = view.context.getString(R.string.seasons, it)
        }
    }
}

@BindingAdapter(value = ["app:setEpisodes"])
fun setEpisodes(view: TextView, episodesNumber: Int?) {
    episodesNumber?.let {
        when (it) {
            1 -> view.text = view.context.getString(R.string.episode, it)
            else -> view.text = view.context.getString(R.string.episodes, it)
        }
    }
}

@BindingAdapter(value = ["app:setSeasonNumber"])
fun setSeasonNumber(view: TextView, seasonNumber: Int?) {
    seasonNumber?.let {
        view.text = view.context.getString(R.string.season_number, it)
    }
}

@BindingAdapter(value = ["app:showIfTrue"])
fun showIfTrue(view: View, condition: Boolean) {
    view.isVisible = condition
}

@BindingAdapter(value = ["app:showPasswordIfTrue"])
fun showPasswordIfTrue(editText: EditText, condition: Boolean) {
    if (condition) {
        editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
    } else {
        editText.transformationMethod = PasswordTransformationMethod.getInstance()
    }
}

@BindingAdapter(value = ["app:changePasswordIcon"])
fun changePasswordIcon(imageView: ImageView, condition: Boolean) {
    if (condition) {
        imageView.setImageResource(R.drawable.ic_eye_off)
    } else {
        imageView.setImageResource(R.drawable.ic_eye)
    }
}

@BindingAdapter(value = ["app:currentLanguage"])
fun setCurrentLanguage(textView: TextView, currentLanguage: Language?) {
    currentLanguage?.let { language ->
        when (language) {
            Language.ENGLISH -> textView.text = "English"
            Language.ARABIC -> textView.text = "Arabic"
        }
    }
}

@BindingAdapter(value = ["app:chosenLanguage"])
fun setchosenLanguage(radioGroup: RadioGroup, currentLanguage: Language?) {
    currentLanguage?.let { language ->
        when (language) {
            Language.ENGLISH -> radioGroup.check(R.id.english_language_radio_button)
            Language.ARABIC -> radioGroup.check(R.id.arabic_language_radio_button)
        }
    }
}

@BindingAdapter(value = ["app:currentTheme"])
fun setCurrentTheme(textView: TextView, currentTheme: Theme?) {
    currentTheme?.let { theme ->
        when (theme) {
            Theme.LIGHT -> textView.text = "Light"
            Theme.DARK -> textView.text = "Dark"
        }
    }
}

@BindingAdapter(value = ["app:chosenTheme"])
fun setchosenTheme(radioGroup: RadioGroup, currentTheme: Theme?) {
    currentTheme?.let { theme ->
        when (theme) {
            Theme.LIGHT -> radioGroup.check(R.id.light_theme_radio_button)
            Theme.DARK -> radioGroup.check(R.id.dark_theme_radio_button)
        }
    }
}
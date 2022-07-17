package tk.lorddarthart.swapicharacterfinder.util

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import tk.lorddarthart.swapicharacterfinder.di.AppComponent
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.App
import tk.lorddarthart.swapicharacterfinder.util.exceptions.ThrowUtils

object DIUtils {
    val Any.appComponent: AppComponent
        get() = when (this) {
            is Application -> (this as? App)?.appComponent ?: ThrowUtils.throwMissingComponentException(AppComponent::class)
            is Activity -> applicationContext.appComponent
            is Fragment -> requireActivity().applicationContext.appComponent
            else -> ThrowUtils.throwMissingComponentException(AppComponent::class)
        }
}
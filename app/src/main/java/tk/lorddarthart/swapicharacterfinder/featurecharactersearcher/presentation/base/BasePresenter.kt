package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.base

import moxy.MvpPresenter
import moxy.MvpView

/**
 * Base Presenter class for all presenters of application
 */
abstract class BasePresenter<View : MvpView> : MvpPresenter<View>()

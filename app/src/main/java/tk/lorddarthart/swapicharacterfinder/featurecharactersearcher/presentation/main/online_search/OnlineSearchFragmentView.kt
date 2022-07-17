package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.main.online_search

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import tk.lorddarthart.swapicharacterfinder.util.ClickWrapper

/**
 * Created by LordDarthArt on 11.11.2019.
 */
@AddToEndSingle
interface OnlineSearchFragmentView : MvpView, ClickWrapper {
    fun triggerSearch(searchString: String)
    fun triggerError(errorString: String)
    fun triggerRecycler()
    fun showLoadingDialog(show: Boolean)
}
package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.main.history_list

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import tk.lorddarthart.swapicharacterfinder.util.ClickWrapper

/**
 * Created by LordDarthArt on 11.11.2019.
 */
@AddToEndSingle
interface HistoryListFragmentView : MvpView, ClickWrapper {
    fun triggerRecycler()
}
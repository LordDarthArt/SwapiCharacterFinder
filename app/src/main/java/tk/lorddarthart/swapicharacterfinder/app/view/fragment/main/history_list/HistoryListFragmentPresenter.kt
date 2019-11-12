package tk.lorddarthart.swapicharacterfinder.app.view.fragment.main.history_list

import android.view.View
import com.arellomobile.mvp.InjectViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tk.lorddarthart.swapicharacterfinder.app.App
import tk.lorddarthart.swapicharacterfinder.app.model.AppDatabase
import tk.lorddarthart.swapicharacterfinder.app.model.entity.SwapiCharEntity
import tk.lorddarthart.swapicharacterfinder.app.view.base.BasePresenter
import tk.lorddarthart.swapicharacterfinder.util.helper.OnItemTouchListener

/**
 * Created by LordDarthArt on 11.11.2019.
 */
@InjectViewState
class HistoryListFragmentPresenter : BasePresenter<HistoryListFragmentView>() {
    var char: SwapiCharEntity? = null
    var characterList: List<SwapiCharEntity>? = null
    var itemTouchListener: OnItemTouchListener? = null

    fun begin() {
        GlobalScope.launch(Dispatchers.IO) {
            characterList = AppDatabase.getInstance(App.instance).swapiCharDao().getListOfSwapiChars()
            GlobalScope.launch(Dispatchers.Main) {
                viewState.triggerRecycler()
            }
        }

        itemTouchListener = object : OnItemTouchListener {
            override fun onCardViewTap(view: View, position: Int) {
                val char = characterList?.get(position)
                this@HistoryListFragmentPresenter.char = char
                viewState.onClick(view)
            }
        }
    }
}
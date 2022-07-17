package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.main.history_list

import android.content.Context
import android.view.View
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.presenterScope
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.App
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.data.datasource.SwapiDatabase
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.domain.vo.SwapiChar
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.base.BasePresenter
import tk.lorddarthart.swapicharacterfinder.util.helper.OnItemTouchListener
import tk.lorddarthart.swapicharacterfinder.util.mapper.SwapiMapper

/**
 * Created by LordDarthArt on 11.11.2019.
 */
@InjectViewState
class HistoryListFragmentPresenter(
    private val applicationContext: Context
) : BasePresenter<HistoryListFragmentView>() {
    var char: SwapiChar? = null
    var characterList: List<SwapiChar>? = null
    var itemTouchListener: OnItemTouchListener? = null

    fun begin() {
        presenterScope.launch(Dispatchers.IO) {
            characterList = SwapiDatabase.getInstance(applicationContext)
                .swapiCharDao()
                .getListOfSwapiChars()
                .map { SwapiMapper.toViewObject(it) }
            withContext(Dispatchers.Main) {
                viewState.triggerRecycler()
            }
        }

        itemTouchListener = object : OnItemTouchListener {
            override fun onCardViewTap(view: View, position: Int) {
                val char = characterList?.get(position)
                this@HistoryListFragmentPresenter.char = char
                viewState.click(view)
            }
        }
    }
}
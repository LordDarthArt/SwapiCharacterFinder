package tk.lorddarthart.swapicharacterfinder.app.view.fragment.character_info

import com.arellomobile.mvp.InjectViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tk.lorddarthart.swapicharacterfinder.app.App
import tk.lorddarthart.swapicharacterfinder.app.model.AppDatabase
import tk.lorddarthart.swapicharacterfinder.app.model.entity.SwapiCharEntity
import tk.lorddarthart.swapicharacterfinder.app.model.request.SwapiChar
import tk.lorddarthart.swapicharacterfinder.app.view.base.BasePresenter

/**
 * Created by LordDarthArt on 10.11.2019.
 */
@InjectViewState
class CharacterInfoFragmentPresenter : BasePresenter<CharacterInfoFragmentView>() {
    var swapiChar: SwapiCharEntity? =  null
    var swapiCharName: String? = null


    fun loadDataFromDB() {
        GlobalScope.launch(Dispatchers.IO) {
            swapiChar = AppDatabase.getInstance(App.instance).swapiCharDao().getSwapiCharByName(
                swapiCharName!!
            )
            GlobalScope.launch(Dispatchers.Main) {
                viewState.triggerFragment(swapiChar)
            }
        }
    }
}
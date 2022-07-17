package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.character_info

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.presenterScope
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.App
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.data.datasource.SwapiDatabase
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.domain.entity.SwapiCharEntity
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.base.BasePresenter

/**
 * Created by LordDarthArt on 10.11.2019.
 */
@InjectViewState
class CharacterInfoFragmentPresenter(
    private val applicationContext: Context
) : BasePresenter<CharacterInfoFragmentView>() {
    var swapiChar: SwapiCharEntity? = null
    var swapiCharName: String? = null

    suspend fun loadDataFromDB() = flow {
        presenterScope.launch(Dispatchers.IO) {
            swapiCharName?.let {
                swapiChar = SwapiDatabase.getInstance(applicationContext)
                    .swapiCharDao()
                    .getSwapiCharByName(it)
            }
            withContext(Dispatchers.Main) {
                emit { swapiChar?.let { viewState.triggerFragment(it) } }
            }
        }
    }
        .catch { Log.e("CharacterInfoFragmentPresenter", "Unexpected exception", it) }
        .collect { it.invoke() }
}
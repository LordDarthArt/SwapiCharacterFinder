package tk.lorddarthart.swapicharacterfinder.app.view.fragment.character_info

import com.arellomobile.mvp.MvpView
import tk.lorddarthart.swapicharacterfinder.app.model.entity.SwapiCharEntity
import tk.lorddarthart.swapicharacterfinder.app.model.request.SwapiChar

/**
 * Created by LordDarthArt on 10.11.2019.
 */
interface CharacterInfoFragmentView: MvpView {
    fun triggerFragment(targetSwapiChar: SwapiCharEntity?)
}
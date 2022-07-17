package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.character_info

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.domain.entity.SwapiCharEntity

/**
 * Created by LordDarthArt on 10.11.2019.
 */
@AddToEndSingle
interface CharacterInfoFragmentView: MvpView {
    fun triggerFragment(targetSwapiChar: SwapiCharEntity)
}
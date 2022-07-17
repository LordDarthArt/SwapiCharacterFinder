package tk.lorddarthart.swapicharacterfinder.util.mapper

import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.domain.entity.SwapiCharEntity
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.domain.vo.SwapiChar

object SwapiMapper {

    fun toViewObject(entity: SwapiCharEntity) : SwapiChar = SwapiChar(
        swapiCharName = entity.swapiCharName,
        swapiCharBirthYear = entity.swapiCharBirthYear,
        swapiCharEyeColor = entity.swapiCharEyeColor,
        swapiCharGender = entity.swapiCharGender,
        swapiCharHairColor = entity.swapiCharHairColor,
        swapiCharHeight = entity.swapiCharHeight,
        swapiCharMass = entity.swapiCharMass,
        swapiCharSkinColor = entity.swapiCharSkinColor
    )
}
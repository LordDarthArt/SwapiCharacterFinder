package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.main.history_list.adapter

import androidx.recyclerview.widget.DiffUtil
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.domain.vo.SwapiChar

class SwapiCharDiffUtil : DiffUtil.ItemCallback<SwapiChar>() {
    override fun areItemsTheSame(oldItem: SwapiChar, newItem: SwapiChar): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: SwapiChar, newItem: SwapiChar): Boolean {
        return oldItem.swapiCharGender == newItem.swapiCharGender &&
                oldItem.swapiCharName == newItem.swapiCharName &&
                oldItem.swapiCharHeight == newItem.swapiCharHeight &&
                oldItem.swapiCharEyeColor == newItem.swapiCharEyeColor &&
                oldItem.swapiCharMass == newItem.swapiCharMass &&
                oldItem.swapiCharBirthYear == newItem.swapiCharBirthYear &&
                oldItem.swapiCharHairColor == newItem.swapiCharHairColor &&
                oldItem.swapiCharSkinColor == newItem.swapiCharSkinColor
    }
}
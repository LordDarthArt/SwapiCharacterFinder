package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.main.history_list.adapter

import androidx.recyclerview.widget.RecyclerView
import tk.lorddarthart.swapicharacterfinder.databinding.ItemSingleCharacterBinding
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.domain.vo.SwapiChar
import tk.lorddarthart.swapicharacterfinder.util.helper.OnItemTouchListener

class HistoryListViewHolder(
    private val binding: ItemSingleCharacterBinding
) : RecyclerView.ViewHolder(binding.root) {

    constructor(binding: ItemSingleCharacterBinding, onClick: OnItemTouchListener) : this(binding) {
        binding.root.setOnClickListener {
            onClick.onCardViewTap(binding.root, layoutPosition)
        }
    }

    fun bind(char: SwapiChar) {
        binding.characterName.text = char.swapiCharName
    }
}
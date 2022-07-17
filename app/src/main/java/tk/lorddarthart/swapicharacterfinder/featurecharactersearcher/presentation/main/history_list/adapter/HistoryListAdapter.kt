package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.main.history_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import tk.lorddarthart.swapicharacterfinder.R
import tk.lorddarthart.swapicharacterfinder.databinding.ItemSingleCharacterBinding
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.domain.vo.SwapiChar
import tk.lorddarthart.swapicharacterfinder.util.helper.OnItemTouchListener

class HistoryListAdapter(
    private val onClick: OnItemTouchListener
) : ListAdapter<SwapiChar, HistoryListViewHolder>(SwapiCharDiffUtil()) {

    override fun onBindViewHolder(holder: HistoryListViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryListViewHolder {
        val binding = ItemSingleCharacterBinding.bind(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_single_character, parent, false)
        )

        return HistoryListViewHolder(binding, onClick)
    }
}
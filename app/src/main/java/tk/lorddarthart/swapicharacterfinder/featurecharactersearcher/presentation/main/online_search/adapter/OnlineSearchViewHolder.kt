package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.main.online_search.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tk.lorddarthart.swapicharacterfinder.R
import tk.lorddarthart.swapicharacterfinder.util.helper.OnItemTouchListener

class OnlineSearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val characterName: TextView = itemView.findViewById(R.id.character_name)

    constructor(itemView: View, onClick: OnItemTouchListener) : this(itemView) {
        itemView.setOnClickListener {
            onClick.onCardViewTap(itemView, layoutPosition)
        }
    }
}
package tk.lorddarthart.swapicharacterfinder.app.view.fragment.main.history_list.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tk.lorddarthart.swapicharacterfinder.R
import tk.lorddarthart.swapicharacterfinder.util.helper.OnItemTouchListener

class HistoryListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val characterName: TextView = itemView.findViewById(R.id.character_name)

    constructor(itemView: View, onClick: OnItemTouchListener) : this(itemView) {
        itemView.setOnClickListener {
            onClick.onCardViewTap(itemView, layoutPosition)
        }
    }
}
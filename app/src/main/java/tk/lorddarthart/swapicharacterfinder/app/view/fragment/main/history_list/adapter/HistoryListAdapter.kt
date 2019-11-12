package tk.lorddarthart.swapicharacterfinder.app.view.fragment.main.history_list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tk.lorddarthart.swapicharacterfinder.R
import tk.lorddarthart.swapicharacterfinder.app.App
import tk.lorddarthart.swapicharacterfinder.app.model.entity.SwapiCharEntity
import tk.lorddarthart.swapicharacterfinder.util.helper.OnItemTouchListener

class HistoryListAdapter(
    private val swapiCharList: List<SwapiCharEntity?>?,
    private val onClick: OnItemTouchListener
) : RecyclerView.Adapter<HistoryListViewHolder>() {
    private lateinit var singleUserView: View
    private var singleAlbumViewHolder: HistoryListViewHolder? = null

    override fun getItemCount(): Int {
        swapiCharList?.size?.let {
            return it
        }
        return 0
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HistoryListViewHolder, position: Int) {
        val character = swapiCharList?.get(position)
        with(holder) {
            characterName.text = character?.swapiCharName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryListViewHolder {
        singleUserView = LayoutInflater.from(App.instance).inflate(
            R.layout.item_single_character,
            parent,
            false
        )

        singleAlbumViewHolder = HistoryListViewHolder(singleUserView, onClick)
        return singleAlbumViewHolder as HistoryListViewHolder
    }
}
package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.main.online_search.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tk.lorddarthart.swapicharacterfinder.R
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.App
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.domain.dto.SwapiCharDto
import tk.lorddarthart.swapicharacterfinder.util.helper.OnItemTouchListener

class OnlineSearchAdapter(
    private val swapiCharList: List<SwapiCharDto?>?,
    private val onClick: OnItemTouchListener
) : RecyclerView.Adapter<OnlineSearchViewHolder>() {
    private lateinit var singleUserView: View
    private var singleAlbumViewHolder: OnlineSearchViewHolder? = null

    override fun getItemCount(): Int {
        swapiCharList?.size?.let {
            return it
        }
        return 0
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: OnlineSearchViewHolder, position: Int) {
        val character = swapiCharList?.get(position)
        with(holder) {
            characterName.text = character?.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnlineSearchViewHolder {
        singleUserView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_single_character,
            parent,
            false
        )

        singleAlbumViewHolder = OnlineSearchViewHolder(singleUserView, onClick)
        return singleAlbumViewHolder as OnlineSearchViewHolder
    }
}
package tk.lorddarthart.swapicharacterfinder.app.view.fragment.main.online_search.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tk.lorddarthart.swapicharacterfinder.R
import tk.lorddarthart.swapicharacterfinder.app.App
import tk.lorddarthart.swapicharacterfinder.app.model.request.SwapiChar
import tk.lorddarthart.swapicharacterfinder.util.helper.OnItemTouchListener

class OnlineSearchAdapter(
    private val swapiCharList: List<SwapiChar?>?,
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
        singleUserView = LayoutInflater.from(App.instance).inflate(
            R.layout.item_single_character,
            parent,
            false
        )

        singleAlbumViewHolder = OnlineSearchViewHolder(singleUserView, onClick)
        return singleAlbumViewHolder as OnlineSearchViewHolder
    }
}
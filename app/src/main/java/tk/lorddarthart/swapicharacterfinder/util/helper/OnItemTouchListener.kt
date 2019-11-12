package tk.lorddarthart.swapicharacterfinder.util.helper

import android.view.View

/**
 * Created by LordDarthArt on 10.11.2019.
 */
interface OnItemTouchListener {
    /**
     * Callback invoked when the user Taps one of the RecyclerView items
     *
     * @param view     the CardView touched
     * @param position the index of the item touched in the RecyclerView
     */
    fun onCardViewTap(view: View, position: Int)
}
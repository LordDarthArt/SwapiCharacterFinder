package tk.lorddarthart.swapicharacterfinder.util

import android.view.View
import moxy.viewstate.strategy.alias.AddToEndSingle

interface ClickWrapper {
    @AddToEndSingle
    fun click(v: View?)
}
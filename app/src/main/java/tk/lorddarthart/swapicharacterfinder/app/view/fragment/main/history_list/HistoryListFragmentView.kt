package tk.lorddarthart.swapicharacterfinder.app.view.fragment.main.history_list

import android.view.View
import com.arellomobile.mvp.MvpView

/**
 * Created by LordDarthArt on 11.11.2019.
 */
interface HistoryListFragmentView: MvpView, View.OnClickListener {
    fun triggerRecycler()
}
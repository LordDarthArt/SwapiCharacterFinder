package tk.lorddarthart.swapicharacterfinder.app.view.fragment.main.online_search

import android.view.View
import com.arellomobile.mvp.MvpView

/**
 * Created by LordDarthArt on 11.11.2019.
 */
interface OnlineSearchFragmentView: MvpView, View.OnClickListener {
    fun triggerSearch(searchString: String)

    fun triggerError(errorString: String)

    fun triggerRecycler()

    fun showLoadingDialog(show: Boolean)
}
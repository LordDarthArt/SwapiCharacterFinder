package tk.lorddarthart.swapicharacterfinder.app.view.fragment.main.history_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import tk.lorddarthart.swapicharacterfinder.R
import tk.lorddarthart.swapicharacterfinder.app.App
import tk.lorddarthart.swapicharacterfinder.app.view.base.BaseFragment
import tk.lorddarthart.swapicharacterfinder.app.view.fragment.character_info.CharacterInfoFragment
import tk.lorddarthart.swapicharacterfinder.app.view.fragment.main.history_list.adapter.HistoryListAdapter
import tk.lorddarthart.swapicharacterfinder.app.view.fragment.main.online_search.adapter.OnlineSearchAdapter
import tk.lorddarthart.swapicharacterfinder.databinding.FragmentHistoryListBinding

/**
 * Created by LordDarthArt on 11.11.2019.
 */
class HistoryListFragment: BaseFragment(), HistoryListFragmentView {
    private lateinit var historyListFragmentBinding: FragmentHistoryListBinding

    @InjectPresenter
    lateinit var historyListFragmentPresenter: HistoryListFragmentPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        historyListFragmentBinding = FragmentHistoryListBinding.inflate(
            inflater,
            container,
            false
        )

        initialization()

        return historyListFragmentBinding.root
    }

    private fun initialization() {
        start()
        initListeners()
    }

    private fun start() {
        historyListFragmentPresenter.begin()

        historyListFragmentBinding.historyOverlookedCharactersList.layoutManager = LinearLayoutManager(App.instance)
        historyListFragmentBinding.historyOverlookedCharactersList.adapter = HistoryListAdapter(historyListFragmentPresenter.characterList,
            historyListFragmentPresenter.itemTouchListener!!
        )
    }

    private fun initListeners() {

    }

    override fun onClick(v: View?) {
        activity.supportFragmentManager.beginTransaction()
            .add(
                R.id.main_container,
                CharacterInfoFragment.newInstance(
                    historyListFragmentPresenter.char!!
                )
            ).addToBackStack(null)
            .commitAllowingStateLoss()
    }

    override fun triggerRecycler() {
        historyListFragmentBinding.historyOverlookedCharactersList.adapter = HistoryListAdapter(historyListFragmentPresenter.characterList,
            historyListFragmentPresenter.itemTouchListener!!)
    }
}
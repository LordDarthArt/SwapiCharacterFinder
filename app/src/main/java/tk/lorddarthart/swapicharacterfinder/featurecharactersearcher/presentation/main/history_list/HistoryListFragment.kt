package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.main.history_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.ktx.moxyPresenter
import tk.lorddarthart.swapicharacterfinder.R
import tk.lorddarthart.swapicharacterfinder.databinding.FragmentHistoryListBinding
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.base.BaseFragment
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.character_info.CharacterInfoFragment
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.main.history_list.adapter.HistoryListAdapter

/**
 * Created by LordDarthArt on 11.11.2019.
 */
class HistoryListFragment : BaseFragment<FragmentHistoryListBinding, HistoryListFragmentPresenter>(), HistoryListFragmentView, View.OnClickListener {

    override val presenter by moxyPresenter { presenterProvider.get() }

    override fun initialization(inflater: LayoutInflater, container: ViewGroup?) {
        binding = initializeBinding(inflater, container)
        start()
        initListeners()
    }

    override fun start() {
        presenter.begin()

        binding?.historyOverlookedCharactersList?.apply {
            adapter = HistoryListAdapter(requireNotNull(presenter.itemTouchListener))
            presenter.characterList?.let {
                (adapter as? HistoryListAdapter)?.submitList(it)
            }
        }
    }

    override fun initListeners() {

    }

    override fun click(v: View?) {
        onClick(v)
    }

    override fun onClick(v: View?) {
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.main_container, CharacterInfoFragment())
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    override fun triggerRecycler() {
        (binding?.historyOverlookedCharactersList?.adapter as? HistoryListAdapter)?.submitList(
            presenter.characterList
        )
    }

    override fun initializeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHistoryListBinding.inflate(
        inflater,
        container,
        false
    )
}
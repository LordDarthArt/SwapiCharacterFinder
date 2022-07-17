package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import moxy.ktx.moxyPresenter
import tk.lorddarthart.swapicharacterfinder.R
import tk.lorddarthart.swapicharacterfinder.databinding.FragmentMainBinding
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.MainActivity
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.base.BaseFragment
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.main.history_list.HistoryListFragment
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.main.online_search.OnlineSearchFragment
import tk.lorddarthart.swapicharacterfinder.util.helper.IOnBackPressed


class MainFragment : BaseFragment<FragmentMainBinding, MainFragmentPresenter>(), MainFragmentView,
    IOnBackPressed {

    override val presenter by moxyPresenter { presenterProvider.get() }

    override fun initialization(inflater: LayoutInflater, container: ViewGroup?) {
        binding = initializeBinding(inflater, container)
        start()
        initListeners()
    }

    override fun initializeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMainBinding.inflate(
        inflater,
        container,
        false
    )

    override fun start() {
        (requireActivity() as? MainActivity)?.apply {
            supportActionBar?.title = getString(R.string.app_name)
            supportActionBar?.setDisplayShowTitleEnabled(true)
            setSupportActionBar(binding?.mainFragmentToolbar)
        }
        initializeOnlineFragment()
    }

    override fun initListeners() {
        binding?.mainFragmentBottomNavigationView?.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.search_chars_online -> {
                    initializeOnlineFragment()
                    true
                }
                R.id.local_chars_offline -> {
                    initializeOfflineFragment()
                    true
                }
                else -> {
                    // Are you kidding me?
                    false
                }
            }
        }
    }

    fun initializeOnlineFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, OnlineSearchFragment())
            .commitAllowingStateLoss()
    }

    fun initializeOfflineFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, HistoryListFragment())
            .commitAllowingStateLoss()
    }

    override fun onBackPressed() {
        showExitDialog()
    }
}
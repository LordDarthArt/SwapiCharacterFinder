package tk.lorddarthart.swapicharacterfinder.app.view.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import tk.lorddarthart.swapicharacterfinder.R
import tk.lorddarthart.swapicharacterfinder.app.view.base.BaseFragment
import tk.lorddarthart.swapicharacterfinder.app.view.fragment.main.history_list.HistoryListFragment
import tk.lorddarthart.swapicharacterfinder.app.view.fragment.main.online_search.OnlineSearchFragment
import tk.lorddarthart.swapicharacterfinder.databinding.FragmentMainBinding
import tk.lorddarthart.swapicharacterfinder.util.helper.IOnBackPressed


class MainFragment : BaseFragment(), MainFragmentView, IOnBackPressed {
    private lateinit var mainFragmentBinding: FragmentMainBinding

    @InjectPresenter
    lateinit var mainFragmentPresenter: MainFragmentPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainFragmentBinding = FragmentMainBinding.inflate(
            inflater,
            container,
            false
        )

        initialization()

        return mainFragmentBinding.root
    }

    private fun initialization() {
        start()
        initListeners()
    }

    private fun start() {
        activity.supportActionBar?.title = getString(R.string.app_name)
        activity.supportActionBar?.setDisplayShowTitleEnabled(true)
        activity.setSupportActionBar(mainFragmentBinding.mainFragmentToolbar)
        initializeOnlineFragment()
    }

    private fun initListeners() {
        mainFragmentBinding.mainFragmentBottomNavigationView.setOnNavigationItemSelectedListener {
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
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, OnlineSearchFragment())
            .commitAllowingStateLoss()
    }

    fun initializeOfflineFragment() {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, HistoryListFragment())
            .commitAllowingStateLoss()
    }

    override fun onBackPressed() {
        showExitDialog()
    }
}
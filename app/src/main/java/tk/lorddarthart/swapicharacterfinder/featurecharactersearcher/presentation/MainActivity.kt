package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation

import android.os.Bundle
import moxy.ktx.moxyPresenter
import tk.lorddarthart.swapicharacterfinder.R
import tk.lorddarthart.swapicharacterfinder.databinding.ActivityMainBinding
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.base.BaseActivity
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.main.MainFragment
import tk.lorddarthart.swapicharacterfinder.util.helper.IOnBackPressed
import tk.lorddarthart.swapicharacterfinder.util.logs.Loggable
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : BaseActivity(), MainActivityView, Loggable {
    var swapiCharName: String? = null
    private var binding: ActivityMainBinding? = null

    @Inject
    lateinit var presenterProvider: Provider<MainActivityPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    fun inflateMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(binding?.mainContainer?.id ?: 0, MainFragment())
            .commitAllowingStateLoss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        initialization()
    }

    private fun initialization() {
        start()
    }

    private fun start() {
        inflateMainFragment()
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.main_fragment_container)

        if (currentFragment is IOnBackPressed) {
            currentFragment.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return true
    }
}

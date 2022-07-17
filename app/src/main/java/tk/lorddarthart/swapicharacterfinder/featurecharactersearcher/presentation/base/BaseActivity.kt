package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.base

import android.os.Bundle
import dagger.android.AndroidInjection
import moxy.MvpAppCompatActivity

/**
 * Base Activity class that is parent to other activities of this application
 */
abstract class BaseActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }
}
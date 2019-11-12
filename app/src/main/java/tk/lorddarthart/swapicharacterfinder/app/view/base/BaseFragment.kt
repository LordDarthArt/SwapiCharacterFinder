package tk.lorddarthart.swapicharacterfinder.app.view.base

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import tk.lorddarthart.swapicharacterfinder.R
import tk.lorddarthart.swapicharacterfinder.app.model.entity.SwapiCharEntity
import tk.lorddarthart.swapicharacterfinder.app.model.request.SwapiChar
import tk.lorddarthart.swapicharacterfinder.app.view.activity.MainActivity
import tk.lorddarthart.swapicharacterfinder.app.view.fragment.character_info.CharacterInfoFragment
import tk.lorddarthart.swapicharacterfinder.util.moxy.MvpFragment

/**
 * Base Fragment class that is parent to other fragments of this application
 */
open class BaseFragment : MvpFragment() {
    protected lateinit var activity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)

        activity = context as MainActivity
    }



    protected fun showExitDialog() {
        MaterialAlertDialogBuilder(activity)
            .setTitle(getString(R.string.menu_exit))
            .setMessage(getString(R.string.exit_accept_message))
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                activity.finishAffinity()
            }
            .setNeutralButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    fun moveToCharacterInfo(char: SwapiCharEntity, string: String) {
        activity.supportFragmentManager.beginTransaction()
            .add(
                R.id.main_container,
                CharacterInfoFragment.newInstance(
                    char
                )
            ).addToBackStack(null)
            .commitAllowingStateLoss()
    }
}
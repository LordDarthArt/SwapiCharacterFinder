package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import moxy.MvpAppCompatFragment
import moxy.MvpPresenter
import tk.lorddarthart.swapicharacterfinder.R
import tk.lorddarthart.swapicharacterfinder.util.DIUtils.appComponent
import tk.lorddarthart.swapicharacterfinder.util.exceptions.ThrowUtils
import javax.inject.Inject
import javax.inject.Provider

/**
 * Base Fragment class that is parent to other fragments of this application
 */
abstract class BaseFragment <T: ViewBinding, P: MvpPresenter<*>> : MvpAppCompatFragment() {
    protected var binding: T? = null

    @Inject
    lateinit var presenterProvider: Provider<P>

    abstract val presenter: P

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initialization(inflater, container)
        return binding?.root ?: ThrowUtils.throwUninitializedBinding()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    open fun initialization(inflater: LayoutInflater, container: ViewGroup?) {
        binding = initializeBinding(inflater, container)
        start()
        initListeners()
        configure()
    }

    abstract fun initializeBinding(inflater: LayoutInflater, container: ViewGroup?): T

    open fun start() {}

    open fun initListeners() {}

    open fun configure() {}

    protected fun showExitDialog() {
        MaterialAlertDialogBuilder(binding?.root?.context ?: ThrowUtils.throwUninitializedBinding())
            .setTitle(getString(R.string.menu_exit))
            .setMessage(getString(R.string.exit_accept_message))
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                activity?.finishAffinity()
            }
            .setNeutralButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
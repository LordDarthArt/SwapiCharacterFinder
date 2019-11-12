package tk.lorddarthart.swapicharacterfinder.app.view.fragment.main.online_search

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tk.lorddarthart.swapicharacterfinder.R
import tk.lorddarthart.swapicharacterfinder.app.view.base.BaseFragment
import tk.lorddarthart.swapicharacterfinder.app.view.fragment.character_info.CharacterInfoFragment
import tk.lorddarthart.swapicharacterfinder.app.view.fragment.main.online_search.adapter.OnlineSearchAdapter
import tk.lorddarthart.swapicharacterfinder.databinding.FragmentOnlineSearchBinding

/**
 * Created by LordDarthArt on 11.11.2019.
 */
class OnlineSearchFragment : BaseFragment(), OnlineSearchFragmentView {

    private lateinit var onlineSearchFragmentBinding: FragmentOnlineSearchBinding
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private lateinit var loadingDialog: ProgressDialog

    @InjectPresenter
    lateinit var onlineSearchFragmentPresenter: OnlineSearchFragmentPresenter

    override fun triggerSearch(searchString: String) {
        if (!searchString.isBlank() && !onlineSearchFragmentPresenter.beginNetworkRequest) {
            onlineSearchFragmentPresenter.beginNetworkRequest = true
            coroutineScope.launch { request() }
        }
    }

    override fun triggerError(errorString: String) {
        errorString.let { message ->
            activity.runOnUiThread {
                Snackbar.make(onlineSearchFragmentBinding.root, message, Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun triggerRecycler() {
        onlineSearchFragmentBinding.fragmentMainListOfUsersFound.adapter =
            OnlineSearchAdapter(
                onlineSearchFragmentPresenter.charsList,
                onlineSearchFragmentPresenter.itemTouchListener!!
            )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onlineSearchFragmentBinding = FragmentOnlineSearchBinding.inflate(
            inflater,
            container,
            false
        )

        retainInstance = true

        initialization()

        return onlineSearchFragmentBinding.root
    }

    private fun initialization() {
        start()
        initListeners()
        configure()
    }

    private fun configure() {
        // For RecyclerView's better performance
        with(onlineSearchFragmentBinding.fragmentMainListOfUsersFound) {
            setHasFixedSize(true)
            setItemViewCacheSize(20)
            isDrawingCacheEnabled = true
            drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
        }
    }

    private fun start() {
        onlineSearchFragmentPresenter.begin()

        loadingDialog = ProgressDialog(activity)
            .apply {
                setTitle("")
                setMessage(getString(R.string.updating))
                setCancelable(false)
                create()
            }

        onlineSearchFragmentBinding.fragmentMainListOfUsersFound.layoutManager =
            LinearLayoutManager(activity)
    }

    private fun initListeners() {
        onlineSearchFragmentBinding.fragmentMainButtonSearch.setOnClickListener {
            onlineSearchFragmentPresenter.searchString =
                onlineSearchFragmentBinding.fragmentMainSearchField.text.toString()
            coroutineScope.launch { request() }
        }
    }

    private suspend fun request() {
        delay(600)
        onlineSearchFragmentPresenter.fetchData()
    }

    override fun onClick(v: View?) {
        onlineSearchFragmentPresenter.saveCharToDb()
        activity.supportFragmentManager.beginTransaction()
            .add(
                R.id.main_container,
                CharacterInfoFragment.newInstance(
                    onlineSearchFragmentPresenter.swapiChar!!
                )
            ).addToBackStack(null)
            .commitAllowingStateLoss()
    }

    override fun showLoadingDialog(show: Boolean) {
        if (show) {
            if (::loadingDialog.isInitialized) {
                activity.runOnUiThread { loadingDialog.show() }
            }
        } else {
            if (::loadingDialog.isInitialized) {
                activity.runOnUiThread { loadingDialog.cancel() }
            }
        }
    }
}
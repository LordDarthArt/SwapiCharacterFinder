package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.main.online_search

import android.app.ProgressDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import moxy.ktx.moxyPresenter
import tk.lorddarthart.swapicharacterfinder.R
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.base.BaseFragment
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.character_info.CharacterInfoFragment
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.main.online_search.adapter.OnlineSearchAdapter
import tk.lorddarthart.swapicharacterfinder.databinding.FragmentOnlineSearchBinding

/**
 * Created by LordDarthArt on 11.11.2019.
 */
class OnlineSearchFragment : BaseFragment<FragmentOnlineSearchBinding, OnlineSearchFragmentPresenter>(), OnlineSearchFragmentView, View.OnClickListener {

    override val presenter by moxyPresenter { presenterProvider.get() }

    private lateinit var loadingDialog: ProgressDialog

    override fun triggerSearch(searchString: String) {
        if (searchString.isNotBlank() && !presenter.beginNetworkRequest) {
            presenter.beginNetworkRequest = true
            lifecycleScope.launch(Dispatchers.IO) { request() }
        }
    }

    override fun triggerError(errorString: String) {
        errorString.let { message ->
            lifecycleScope.launch(Dispatchers.Main) {
                binding?.root?.let {
                    Snackbar.make(it, message, Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    override fun triggerRecycler() {
        binding?.fragmentMainListOfUsersFound?.adapter =
            OnlineSearchAdapter(
                presenter.charsList,
                presenter.itemTouchListener!!
            )
    }

    override fun configure() {
        // For RecyclerView's better performance
        binding?.fragmentMainListOfUsersFound?.apply {
            setHasFixedSize(true)
            setItemViewCacheSize(20)
            isDrawingCacheEnabled = true
            drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
        }
    }

    override fun start() {
        presenter.begin()

        loadingDialog = ProgressDialog(activity)
            .apply {
                setTitle("")
                setMessage(getString(R.string.updating))
                setCancelable(false)
                create()
            }
    }

    override fun initListeners() {
        binding?.fragmentMainButtonSearch?.setOnClickListener {
            presenter.searchString =
                binding?.fragmentMainSearchField?.text.toString()
            lifecycleScope.launch(Dispatchers.IO) { request() }
        }
    }

    private fun request() = presenter.fetchData()

    override fun click(v: View?) {
        onClick(v)
    }

    override fun onClick(v: View?) {
        presenter.saveCharToDb()
        activity?.supportFragmentManager?.beginTransaction()
            ?.add(
                R.id.main_container,
                CharacterInfoFragment()
            )?.addToBackStack(null)
            ?.commitAllowingStateLoss()
    }

    override fun showLoadingDialog(show: Boolean) {
        if (show) {
            if (::loadingDialog.isInitialized) {
                activity?.runOnUiThread { loadingDialog.show() }
            }
        } else {
            if (::loadingDialog.isInitialized) {
                activity?.runOnUiThread { loadingDialog.cancel() }
            }
        }
    }

    override fun initializeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentOnlineSearchBinding.inflate(
        inflater,
        container,
        false
    )
}
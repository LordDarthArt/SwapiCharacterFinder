package tk.lorddarthart.swapicharacterfinder.app.view.fragment.main.online_search

import android.view.View
import com.arellomobile.mvp.InjectViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tk.lorddarthart.swapicharacterfinder.app.App
import tk.lorddarthart.swapicharacterfinder.app.model.AppDatabase
import tk.lorddarthart.swapicharacterfinder.app.model.entity.SwapiCharEntity
import tk.lorddarthart.swapicharacterfinder.app.model.request.SwapiChar
import tk.lorddarthart.swapicharacterfinder.app.model.request.SwapiCharRequestResponse
import tk.lorddarthart.swapicharacterfinder.app.view.base.BasePresenter
import tk.lorddarthart.swapicharacterfinder.util.helper.OnItemTouchListener
import tk.lorddarthart.swapicharacterfinder.util.network.HttpServiceHelper

/**
 * Created by LordDarthArt on 11.11.2019.
 */
@InjectViewState
class OnlineSearchFragmentPresenter: BasePresenter<OnlineSearchFragmentView>() {
    var swapiChar: SwapiCharEntity? = null
    var itemTouchListener: OnItemTouchListener? = null
    var searchString: String? = null
    var beginNetworkRequest = false
    var searchForCharString: String? = null

    lateinit var charsList: List<SwapiChar>

    fun fetchData() {
        viewState.showLoadingDialog(true)
        GlobalScope.launch(Dispatchers.IO) {
            searchString?.let {
                HttpServiceHelper.instance?.jsonApi?.getAlbum(it)
                    ?.enqueue(object : Callback<SwapiCharRequestResponse> {
                        override fun onResponse(
                            call: Call<SwapiCharRequestResponse>,
                            response: Response<SwapiCharRequestResponse>
                        ) {
                            val responseCharsList = response.body()?.results
                            responseCharsList?.let { charsList ->
                                this@OnlineSearchFragmentPresenter.charsList = charsList.sortedBy {  it.name.toString()  }
                            }
                            viewState.triggerRecycler()
                            viewState.showLoadingDialog(false)
                        }

                        override fun onFailure(call: Call<SwapiCharRequestResponse>, t: Throwable) {
                            t.message?.let { it1 -> viewState.triggerError(it1) }
                            viewState.showLoadingDialog(false)
                        }
                    })
            }
        }
    }

    fun begin() {
        searchString = null
        beginNetworkRequest = false

        itemTouchListener = object : OnItemTouchListener {
            override fun onCardViewTap(view: View, position: Int) {
                val char = charsList[position]
                searchForCharString = "${char.name}"
                this@OnlineSearchFragmentPresenter.swapiChar = SwapiCharEntity(
                    char.name!!,
                    char.height!!,
                    char.mass!!,
                    char.hair_color!!,
                    char.skin_color!!,
                    char.eye_color!!,
                    char.birth_year!!,
                    char.gender!!
                )
                viewState.onClick(view)
            }
        }
    }

    fun saveCharToDb() {
        GlobalScope.launch(Dispatchers.IO) {
            AppDatabase.getInstance(App.instance).swapiCharDao().addSwapiChar(swapiChar!!)
        }
    }
}
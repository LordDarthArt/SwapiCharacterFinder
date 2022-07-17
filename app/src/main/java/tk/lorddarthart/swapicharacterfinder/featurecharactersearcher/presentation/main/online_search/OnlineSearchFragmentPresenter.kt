package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.main.online_search

import android.content.Context
import android.view.View
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.presenterScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.App
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.data.datasource.SwapiDatabase
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.domain.dto.SwapiCharDto
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.domain.dto.SwapiCharsDto
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.domain.entity.SwapiCharEntity
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.base.BasePresenter
import tk.lorddarthart.swapicharacterfinder.util.helper.OnItemTouchListener
import tk.lorddarthart.swapicharacterfinder.util.network.HttpServiceHelper

/**
 * Created by LordDarthArt on 11.11.2019.
 */
@InjectViewState
class OnlineSearchFragmentPresenter(
    private val applicationContext: Context
) : BasePresenter<OnlineSearchFragmentView>() {
    var swapiChar: SwapiCharEntity? = null
    var itemTouchListener: OnItemTouchListener? = null
    var searchString: String? = null
    var beginNetworkRequest = false
    var searchForCharString: String? = null

    lateinit var charsList: List<SwapiCharDto>

    fun fetchData() {
        viewState.showLoadingDialog(true)
        presenterScope.launch(Dispatchers.IO) {
            searchString?.let {
                HttpServiceHelper.instance?.jsonApi?.getAlbum(it)
                    ?.enqueue(object : Callback<SwapiCharsDto> {
                        override fun onResponse(
                            call: Call<SwapiCharsDto>,
                            response: Response<SwapiCharsDto>
                        ) {
                            val responseCharsList = response.body()?.results
                            responseCharsList?.let { charsList ->
                                this@OnlineSearchFragmentPresenter.charsList =
                                    charsList.sortedBy { it.name.toString() }
                            }
                            viewState.triggerRecycler()
                            viewState.showLoadingDialog(false)
                        }

                        override fun onFailure(call: Call<SwapiCharsDto>, t: Throwable) {
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
                viewState.click(view)
            }
        }
    }

    fun saveCharToDb() {
        presenterScope.launch(Dispatchers.IO) {
            SwapiDatabase.getInstance(applicationContext)
                .swapiCharDao()
                .addSwapiChar(requireNotNull(swapiChar))
        }
    }
}
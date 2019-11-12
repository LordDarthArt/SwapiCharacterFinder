package tk.lorddarthart.swapicharacterfinder.util.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import tk.lorddarthart.swapicharacterfinder.app.model.request.SwapiCharRequestResponse
import tk.lorddarthart.swapicharacterfinder.util.constants.UrlConstants.SEARCH_PARAMETER_SEARCH
import tk.lorddarthart.swapicharacterfinder.util.constants.UrlConstants.SEARCH_URL

interface JSONPlaceHolderApi {
    @GET(SEARCH_URL)
    fun getAlbum(
        @Query(value = SEARCH_PARAMETER_SEARCH, encoded = true) name: String
    ): Call<SwapiCharRequestResponse>
}
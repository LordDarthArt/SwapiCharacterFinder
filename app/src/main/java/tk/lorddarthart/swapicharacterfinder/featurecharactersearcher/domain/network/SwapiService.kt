package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.domain.network

import retrofit2.http.GET
import retrofit2.http.Query
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.domain.dto.SwapiCharDto
import tk.lorddarthart.swapicharacterfinder.util.constants.UrlConstants

interface SwapiService {

    @GET(UrlConstants.SEARCH_URL)
    suspend fun searchChar(
        @Query(value = UrlConstants.SEARCH_PARAMETER_SEARCH, encoded = true) name: String
    ): SwapiCharDto
}
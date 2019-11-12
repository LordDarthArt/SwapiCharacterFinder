package tk.lorddarthart.swapicharacterfinder.app.model.request

import com.google.gson.annotations.Expose

class SwapiCharRequestResponse {

    @Expose
    var count: Int? = null

    @Expose
    var next: String? = null

    @Expose
    var previous: String? = null

    @Expose
    var results: List<SwapiChar>? = null
}
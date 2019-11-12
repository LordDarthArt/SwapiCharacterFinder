package tk.lorddarthart.swapicharacterfinder.app.model.request

import com.google.gson.annotations.Expose

// SwapiChar model with info we are receiving from server
class SwapiChar {
    @Expose
    var name: String? = null

    @Expose
    var height: String? = null

    @Expose
    var mass: String? = null

    @Expose
    var hair_color: String? = null

    @Expose
    var skin_color: String? = null

    @Expose
    var eye_color: String? = null

    @Expose
    var birth_year: String? = null

    @Expose
    var gender: String? = null

    @Expose
    var homeworld: String? = null
}
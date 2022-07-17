package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.domain.dto

// SwapiChar model with info we are receiving from server
data class SwapiCharDto(
    val name: String?,
    val height: String?,
    val mass: String?,
    val hair_color: String?,
    val skin_color: String?,
    val eye_color: String?,
    val birth_year: String?,
    val gender: String?,
    val homeworld: String?,
)
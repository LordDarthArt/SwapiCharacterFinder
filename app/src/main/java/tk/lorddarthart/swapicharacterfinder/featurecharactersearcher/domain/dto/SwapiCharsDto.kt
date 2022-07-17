package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.domain.dto

data class SwapiCharsDto(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<SwapiCharDto>?
)
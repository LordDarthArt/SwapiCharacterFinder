package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.domain.entity.SwapiCharEntity
import tk.lorddarthart.swapicharacterfinder.util.constants.DbConstant.SWAPI_CHAR
import tk.lorddarthart.swapicharacterfinder.util.constants.DbConstant.SWAPI_CHAR_NAME

/**
 * Created by LordDarthArt on 11.11.2019.
 */
@Dao
interface SwapiCharDao {
    @Query("SELECT * FROM $SWAPI_CHAR")
    fun getListOfSwapiChars(): List<SwapiCharEntity>

    @Query("SELECT * FROM $SWAPI_CHAR WHERE $SWAPI_CHAR_NAME = :argName")
    fun getSwapiCharByName(argName: String): SwapiCharEntity

    @Query("SELECT COUNT(*) FROM $SWAPI_CHAR")
    fun recordsCount(): Long

    @Insert(onConflict = REPLACE)
    fun addSwapiChar(char: SwapiCharEntity): Long

    @Delete
    fun removeSwapiChar(char: SwapiCharEntity)
}
package tk.lorddarthart.swapicharacterfinder.app.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import tk.lorddarthart.swapicharacterfinder.util.constants.DbConstant.SWAPI_CHAR
import tk.lorddarthart.swapicharacterfinder.util.constants.DbConstant.SWAPI_CHAR_BIRTH_YEAR
import tk.lorddarthart.swapicharacterfinder.util.constants.DbConstant.SWAPI_CHAR_EYE_COLOR
import tk.lorddarthart.swapicharacterfinder.util.constants.DbConstant.SWAPI_CHAR_GENDER
import tk.lorddarthart.swapicharacterfinder.util.constants.DbConstant.SWAPI_CHAR_HAIR_COLOR
import tk.lorddarthart.swapicharacterfinder.util.constants.DbConstant.SWAPI_CHAR_HEIGHT
import tk.lorddarthart.swapicharacterfinder.util.constants.DbConstant.SWAPI_CHAR_MASS
import tk.lorddarthart.swapicharacterfinder.util.constants.DbConstant.SWAPI_CHAR_NAME
import tk.lorddarthart.swapicharacterfinder.util.constants.DbConstant.SWAPI_CHAR_SKIN_COLOR

/**
 * Created by LordDarthArt on 11.11.2019.
 */
@Entity(tableName = SWAPI_CHAR)
class SwapiCharEntity(
    @PrimaryKey @ColumnInfo(name = SWAPI_CHAR_NAME) val swapiCharName: String,
    @ColumnInfo(name = SWAPI_CHAR_HEIGHT) val swapiCharHeight: String,
    @ColumnInfo(name = SWAPI_CHAR_MASS) val swapiCharMass: String,
    @ColumnInfo(name = SWAPI_CHAR_HAIR_COLOR) val swapiCharHairColor: String,
    @ColumnInfo(name = SWAPI_CHAR_SKIN_COLOR) val swapiCharSkinColor: String,
    @ColumnInfo(name = SWAPI_CHAR_EYE_COLOR) val swapiCharEyeColor: String,
    @ColumnInfo(name = SWAPI_CHAR_BIRTH_YEAR) val swapiCharBirthYear: String,
    @ColumnInfo(name = SWAPI_CHAR_GENDER) val swapiCharGender: String
)
package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.data.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.domain.entity.SwapiCharEntity
import tk.lorddarthart.swapicharacterfinder.util.constants.DbConstant.DATABASE_VERSION

/**
 * Created by LordDarthArt on 11.11.2019.
 */
@Database(entities = [SwapiCharEntity::class], version = DATABASE_VERSION, exportSchema = false)
abstract class SwapiDatabase : RoomDatabase() {
    abstract fun swapiCharDao(): SwapiCharDao

    companion object {

        @Volatile
        private var instance: SwapiDatabase? = null

        fun getInstance(context: Context): SwapiDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): SwapiDatabase {
            return Room.databaseBuilder(context, SwapiDatabase::class.java, DATABASE_NAME)
                .build()
        }

        private const val DATABASE_NAME = "swapi_char_finder.db"
    }
}
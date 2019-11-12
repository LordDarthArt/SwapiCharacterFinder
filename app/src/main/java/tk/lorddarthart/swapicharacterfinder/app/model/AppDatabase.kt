package tk.lorddarthart.swapicharacterfinder.app.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tk.lorddarthart.swapicharacterfinder.app.model.dao.SwapiCharDao
import tk.lorddarthart.swapicharacterfinder.app.model.entity.SwapiCharEntity

private const val DATABASE_VERSION = 4

/**
 * Created by LordDarthArt on 11.11.2019.
 */
@Database(entities = [SwapiCharEntity::class], version = DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun swapiCharDao(): SwapiCharDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .build()
        }

        private const val DATABASE_NAME = "swapi_char_finder.db"
    }
}
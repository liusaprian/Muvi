package com.tiketgrup1.muvi.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tiketgrup1.muvi.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieRoomDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao

    companion object {
        @Volatile
        private var INSTANCE: MovieRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): MovieRoomDatabase {
            if (INSTANCE == null) {
                synchronized(MovieRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MovieRoomDatabase::class.java,
                        "movie_database"
                    ).build()
                }
            }
            return INSTANCE as MovieRoomDatabase
        }
    }
}
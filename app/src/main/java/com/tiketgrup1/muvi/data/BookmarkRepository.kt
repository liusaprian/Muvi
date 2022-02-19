package com.tiketgrup1.muvi.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.tiketgrup1.muvi.database.BookmarkDao
import com.tiketgrup1.muvi.database.MovieRoomDatabase
import com.tiketgrup1.muvi.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookmarkRepository(application: Application) {
    private val bookmarkDao: BookmarkDao

    init {
        val db = MovieRoomDatabase.getDatabase(application)
        bookmarkDao = db.bookmarkDao()
    }

    fun getAllBookmark(): LiveData<List<Movie>> = bookmarkDao.getAllBookmark()

    suspend fun insertBookmark(movie: Movie) {
        withContext(Dispatchers.IO) {
            bookmarkDao.insert(movie)
        }
    }

    suspend fun deleteBookmark(movie: Movie) {
        withContext(Dispatchers.IO) {
            bookmarkDao.delete(movie)
        }
    }
}